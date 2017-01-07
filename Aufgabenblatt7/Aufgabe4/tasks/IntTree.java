class IntTree {
    
    private class Node {
        
        int elem;
        Node left = null;
        Node right = null;
        
        Node(int elem) {
            this.elem = elem;
        }
        
        void add(int elem) {
            if (elem < this.elem) {
                if (this.left == null) {
                    this.left = new Node(elem);
                } else {
                    this.left.add(elem);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(elem);
                } else {
                    this.right.add(elem);
                }
            }
        }
        
        int countNodes() {
            if (this.right == null && this.left == null) return 1;
            int lc = 0, rc = 0;
            if (this.left != null) lc = this.left.countNodes();
            if (this.right != null) rc =  this.right.countNodes();
            return lc + rc + 1;

        }
        
        int countLeaves() {
            int r = 0;
            if (this.left != null) r = left.countLeaves();
            if (this.right != null) r += right.countLeaves();
            if (this.left == null && this.right == null) return 1+r;
            return r;
        }
        
        int height() {
            if (this.left == null && this.right == null) return 1;
            int lc = 0, rc = 0;
            if (this.left != null) lc = this.left.height() +1;
            if (this.right != null) rc = this.right.height() + 1;
            return (lc > rc ? lc : rc); //diese Anweisung ändern oder löschen.
        }
        
        void printLeaves() {
            if (this.left == null && this.right == null) System.out.println(this.elem);
            if (this.left != null) this.left.printLeaves();
            if (this.right != null) this.right.printLeaves();
        }
        
        void printInOrderUp() {
            if (this.left != null) {
                this.left.printInOrderUp();
                System.out.println(this.elem);
                if (this.right != null) {
                    this.right.printInOrderUp();
                }
            } else {
                System.out.println(this.elem);
                if (this.right != null) {
                    this.right.printInOrderUp();
                }
            }
           // if (this.right != null) this.right.printInOrderUp();
           // System.out.println(this.elem);
            //System.out.println(this.left.elem + this.elem + this.right.elem);
        }
        
        void printInOrderUpSub(int elem) {
            //TODO Implementieren Sie hier die Angabe
        }
    
        void printPostOrder() {
            if (this.left != null) {
                left.printPostOrder();
            }
            if (this.right != null) {
                right.printPostOrder();
            }
            System.out.println(this.elem);
        }
    
        void printPreOrder() {
            System.out.println(this.elem);
            if (this.left != null) {
                left.printPreOrder();
            }
            if (this.right != null) {
                right.printPreOrder();
            }
        }
    }
    
    private Node root = null;
    
    public void add(int elem) {
        if (empty()) {
            this.root = new Node(elem);
        } else {
            this.root.add(elem);
        }
    }
    
    public boolean empty() {
        return this.root == null;
    }
    
    public int countNodes() {
        return this.root.countNodes();
    }
    
    public int countLeaves() {
        return this.root.countLeaves();
    }
    
    public int height() {
        return this.root.height();
    }
    
    public void printLeaves() {
        this.root.printLeaves();
    }
    
    public void printInOrderUp() {
        this.root.printInOrderUp();
    }
    
    public void printInOrderUpSub(int elem) {
        Node currentNode = this.root;
        while (currentNode.elem != elem) {
            if (currentNode.elem > elem) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        currentNode.printInOrderUp();
    }
    
    public void printPostOrder(){
        this.root.printPostOrder();
    }
    
    public void printPreOrder(){
        this.root.printPreOrder();
    }
}
