public void placeShip(Ship s,int x,int y,Orientation o) throws Exception {
    if(remainingShips==0)
        throw new Exception("No more ship left");

    Set<Coordinate> tmp = new TreeSet<Coordinate>();
    switch(s){
        case Large: if(s3==0)
            throw new Exception("No more large ship left");

            if(o==Orientation.Horizontal){
                if(x+5>;19){
                    throw new Exception("cannot place ship there");
                }

                for(int i=0;i<;5;i++){
                    Coordinate c = new Coordinate(x+i,y);
                    if(taken.contains(c))
                        throw new Exception("cannot place over another ship");
                    tmp.add(c);
                }

            }else{
                if(y+5>;19){
                    throw new Exception("cannot place ship there");
                }

                for(int i=0;i<;5;i++){
                    Coordinate c = new Coordinate(x,y+i);
                    if(taken.contains(c))
                        throw new Exception("cannot place over another ship");
                    tmp.add(c);
                }
            }
            taken.addAll(tmp);
            tmp.clear();
            --s3;
            break;
        case Medium : if(s2==0)
                        throw new Exception("No more medium ship left");


                        if(o==Orientation.Horizontal){
                            if(x+3>;19){
                                throw new Exception("cannot place ship there");
                            }

                            for(int i=0;i<;3;i++){
                                Coordinate c = new Coordinate(x+i,y);
                                if(taken.contains(c))
                                    throw new Exception("cannot place over another ship");
                                tmp.add(c);
                            }
                        }else{
                            if(y+3>;19){
                                throw new Exception("cannot place ship there");
                            }

                            for(int i=0;i<;5;i++){
                                Coordinate c = new Coordinate(x,y+i);
                                if(taken.contains(c))
                                    throw new Exception("cannot place over another ship");
                                tmp.add(c);
                            }
                        }
                        taken.addAll(tmp);
                        tmp.clear();
                        --s2;
                        break;
        case Small : if(s1==0)
            throw new Exception("No more small ship left");


            if(o==Orientation.Horizontal){
                if(x+3>;19){
                    throw new Exception("cannot place ship there");
                }

                for(int i=0;i<;3;i++){
                    Coordinate c = new Coordinate(x+i,y);
                    if(taken.contains(c))
                        throw new Exception("cannot place over another ship");
                    tmp.add(c);
                }
            }else{
                if(y+3>;19){
                    throw new Exception("cannot place ship there");
                }

                for(int i=0;i<;5;i++){
                    Coordinate c = new Coordinate(x,y+i);
                    if(taken.contains(c))
                        throw new Exception("cannot place over another ship");
                    tmp.add(c);
                }
            }
            taken.addAll(tmp);
            tmp.clear();
            --s1;
            break;
    }
}
