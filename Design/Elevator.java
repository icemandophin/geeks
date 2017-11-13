public class Main {

    private static final int MOVE_INTERVAL = 3;// 状态变化最小时间间隔 3秒

    private static List<Task> taskList = new ArrayList<>();

    private static Queue<UpCmd> upQueue = new PriorityQueue<>();

    private static Queue<DownCmd> downQueue = new PriorityQueue<>();


    private static volatile State state = new State();


    public static void main(String[] args) throws Exception {

        //监控单元
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.format(">>> 当前位于 %d 层，%s", state.position, parseState(state.state)));
            }
        }, 0, 5, TimeUnit.SECONDS);
        //命令输入单元
        Executors.newFixedThreadPool(1).submit(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                String cmd = null;
                while ((cmd = scanner.nextLine()) != null) {
                    String[]arr = cmd.split("\\s+");
                    Task task = new Task(Integer.valueOf(arr[0]),Integer.valueOf(arr[1]));
                    taskList.add(task);
                    System.out.println(cmd);
                }
            }
        });
        //调度单元 上移一层 或 下移一层
        Executors.newScheduledThreadPool(10).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    new CmdHandler().handle();
                    if (upQueue.size()>0) {
                        state.state = 1;
                        if (upQueue.peek().position == state.position) {
                            System.out.println(String.format("【%s】在第 %s 层开门接人（放人）.", parseState(state.state), state.position));
                            while (!upQueue.isEmpty() && upQueue.peek().position == state.position) {
                                upQueue.poll();
                            }
                        }
                        if (!upQueue.isEmpty() && upQueue.peek().position > state.position) {
                            state.position++;
                        }
                    } else if (downQueue.size()>0) {
                        state.state = 2;
                        if (downQueue.peek().position == state.position) {
                            System.out.println(String.format("【%s】在第 %s 层开门接人（放人）.", parseState(state.state), state.position));
                            while (!downQueue.isEmpty() && downQueue.peek().position == state.position) {
                                downQueue.poll();
                            }
                        }
                        if (!downQueue.isEmpty() && downQueue.peek().position < state.position) {
                            state.position--;
                        }
                    } else {
                        state.state = 3;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, 0, 5, TimeUnit.SECONDS);

        //任务整理期

    }

    private static String parseState(int state) {
        switch (state) {
            case 1:
                return "上行中";
            case 2:
                return "下行中";
            case 3:
                return "静止中";
        }
        return "";
    }

    private static class State {
        int state = 3; // 1 上行中 2 下行中 3 静止
        int position = 0;

        public State() {
        }
    }

    private static class Task {
//        int type; // 1 等电梯 2 下电梯
        int position; // 在第几层
        int direction; // 1 向上 2 向下

        public Task(int position, int direction) {
            this.position = position;
            this.direction = direction;
        }
    }

    private static class UpCmd implements Comparable<UpCmd> {

        int position;

        @Override
        public int compareTo(UpCmd o) {
            return position - o.position;
        }
    }

    private static class DownCmd implements Comparable<DownCmd> {
        int position;

        @Override
        public int compareTo(DownCmd o) {
            return o.position - position;
        }
    }

    private static class CmdHandler {
        public void handle() {
            Iterator<Task> iterator = taskList.listIterator();
            if (state.state == 1) {
                /*上行*/
                while (iterator.hasNext()){
                    Task task = iterator.next();
                    if (task.position >= state.position && task.direction == 1) {
                        UpCmd upCmd = new UpCmd();
                        upCmd.position = task.position;
                        upQueue.add(upCmd);
                        iterator.remove();
                    }
                }
            } else if(state.state == 2) {
                //下行
                while (iterator.hasNext()){
                    Task task = iterator.next();
                    if (task.position <= state.position && task.direction == 2) {
                        DownCmd downCmd = new DownCmd();
                        downCmd.position = task.position;
                        downQueue.add(downCmd);
                        iterator.remove();
                    }
                }
            } else{
                if(iterator.hasNext()){
                    Task task = iterator.next();
                    if(task.position>state.position){
                        state.state=1;
                        UpCmd upCmd = new UpCmd();
                        upCmd.position = task.position;
                        upQueue.add(upCmd);
                        iterator.remove();
                    }else {
                        state.state=2;
                        DownCmd downCmd = new DownCmd();
                        downCmd.position = task.position;
                        downQueue.add(downCmd);
                        iterator.remove();
                    }
                }
            }
        }
    }
}
