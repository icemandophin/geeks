/*
similar to merge sort:
recursive merge cur.left and flattened cur.right list
*/
class LinkedList
{
    Node head;

    /* Linked list Node*/
    class Node
    {
        int data;
        Node right, down;
        Node(int data)
        {
            this.data = data;
            right = null;
            down = null;
        }
    }

    // An utility function to merge two sorted linked lists
    Node merge(Node a, Node b)
    {
        // if first linked list is empty then second
        // is the answer
        if (a == null)
        {
            return b;
        }

        // if second linked list is empty then first
        // is the result
        if (b == null)
        {
            return a;
        }

        // compare the data members of the two linked lists
        // and put the larger one in the result
        Node result;

        if (a.data < b.data)
        {
            result = a;
            result.down =  merge(a.down, b);
        }
        else
        {
            result = b;
            result.down = merge(a, b.down);
        }

        return result;
    }

    Node flatten(Node root)
    {
        // Base Cases
        if (root == null || root.right == null)
            return root;

        // recur for list on right
        root.right = flatten(root.right);

        // now merge
        root = merge(root, root.right);

        // return the root
        // it will be in turn merged with its left
        return root;
    }

    /* Utility function to insert a node at begining of the linked list */
    Node push(Node head_ref, int data)
    {
        /* 1 & 2: Allocate the Node & Put in the data*/
        Node new_node = new Node(data);

        /* 3. Make next of new Node as head */
        new_node.down = head_ref;

        /* 4. Move the head to point to new Node */
        head_ref = new_node;

        /*5. return to link it back */
        return head_ref;
    }
}
