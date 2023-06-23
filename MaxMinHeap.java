/**
 * MaxMinHeap used to create a tree, which in every even depth members are greater or equal to their
 * sons, and every odd depth members are smaller or equal to their sons, you can create it from an
 * existing array or add members one by one. in addition the max value will be stored in index 1, and
 * the min value will be stored in index 2 or 3. it offers adding and removing members, keeping its
 * properties.
 * @author Elad Benjo
 * @version May 14th 2023
 */
public class MaxMinHeap
{
    // instance variables - replace the example below with your own
    private double[] _heap;
    private int _heapSize;
    protected static final int MAX_SIZE = 512;
    /**
     * Constructor for objects of class MaxMinHeap
     */
    public MaxMinHeap()
    {
        _heap = new double[MAX_SIZE];
        _heapSize = 0;
    }
    /**
     * @return the size of the heap
     */
    public int getSize()
    {
        return _heapSize;
    }
    /**
     * set the size of the heap if it is valid
     * @param size to set the size of the heap
     */
    public void setSize(int size)
    {
        if (size < 0){
            return;}
        else if (size >= MAX_SIZE){
            _heapSize = MAX_SIZE;}
        else
        _heapSize = size;
    }
    /**
     * set the value of a given index
     * @param i the index to set value
     * @param val the value to set
     */
    public void setVal(int i, double val)
    {
       if (i>0 && i<=MAX_SIZE) //on purpuse to support mainMenu reding from file
           _heap[i] = val;
    }
    /**
     * return the value of a given index if it is inside the heap bounds
     * @param i index of cohice
     * @return value of given index
     */
    public double getVal(int i)
    {
        if (inRange(i))
            return _heap[i];
        return 0;
    }
    /**
     * get the value of the right son
     * @param i index of father
     * @return the value of the right son
     */
    public double getRight(int i)
    {
        return _heap[right(i)];
    }
    /**
     * get the value of the left son
     * @param i index of father
     * @return the value of the left son
     */
    public double getLeft(int i)
    {
        return _heap[left(i)];
    }
    /**
     * get the value of the parent
     * @param i index of son
     * @return the value of the parent
     */
    public double getParent(int i)
    {
        if (i == 1)
            System.exit(0);
        return _heap[parent(i)];
    }
    /**
     * get the index of the right son
     * @param i index of parent
     * @return index of right son
     */
    public int right(int i)
    {
        return ((i*2)+1);
    }
    /**
     * get the index of the left son
     * @param i index of parent
     * @return index of left son
     */
    public int left(int i)
    {
        return (i*2);
    }
    /**
     * get the index of the parent
     * @param i index of son
     * @return index of the parent
     */
    public int parent(int i)
    {
        return i/2;
    }
    public int grandParent(int i)
    {
        return (parent(i)/2);
    }
    /**
     * get the height or depth of a node (root is depth 0)
     * @param i index of given node
     * @return height\depth of node in index i
     */
    public int height(int i)
    {
        int height = (int)(Math.log(i) / Math.log(2));
        return height;
    }
    /**
     * swap between two given nodes
     * @param i index of the first node
     * @param j index of the second node
     */
    public void swap(int i, int j)
    {
        if (!inRange(i) || !inRange(j))
            System.exit(0);
        double iVal = _heap[i];
        double jVal = _heap[j];
        _heap[i] = jVal;
        _heap[j] = iVal;
    }
    /**
     * perform multiple comparisons between given node and all of its sons&grandsons,
     * then swap between nodes so the the node i stores the biggest value out of the sons&grandsons.
     * if it swaps between i and grandson it will compare it to the new father to maintain the min-heap
     * property of the odd depth.
     * @param i index of the node to 'maxHeapify' on
     */
    public void maxHeapify(int i)
    {
        double temp = _heap[i];
        int maxIndex = i;
        int right = right(i);
        int left = left(i);
        int rightRight = right(right);
        int leftRight = left(right);
        int rightLeft = right(left);
        int leftLeft = left(left);
        boolean grandChild = false;
        
        if (inRange(right) && _heap[right] > temp)
        {
            maxIndex = right;
            temp = _heap[right];
        }
        if (inRange(left) && _heap[left] > temp)
        {
            maxIndex = left;
            temp = _heap[left];
        }
        if (inRange(rightRight) && _heap[rightRight] > temp)
        {
            maxIndex = rightRight;
            temp = _heap[rightRight];
            grandChild = true;
        }
        if (inRange(leftRight) && _heap[leftRight] > temp)
        {
            maxIndex = leftRight;
            temp = _heap[leftRight];
            grandChild = true;
        }
        if (inRange(rightLeft) && _heap[rightLeft] > temp)
        {
            maxIndex = rightLeft;
            temp = _heap[rightLeft];
            grandChild = true;
        }
        if (inRange(leftLeft) && _heap[leftLeft] > temp)
        {
            maxIndex = leftLeft;
            temp = _heap[leftLeft];
            grandChild = true;
        }
        
        if (grandChild == true) // if max is a grandchild of i
        {
            if (_heap[maxIndex] > _heap[i])
            {
            this.swap(i,maxIndex);
            if (_heap[maxIndex] < _heap[parent(maxIndex)])// maintain the min heap property
            {
                this.swap(maxIndex,parent(maxIndex));
            }
            this.maxHeapify(maxIndex);
            }
        }
        else if (_heap[maxIndex] > _heap[i])
        {
            this.swap(maxIndex,i);
        }
    }
    /**
     * perform multiple comparisons between given node and all of its sons&grandsons,
     * then swap between nodes so the the node i stores the smallest value out of the sons&grandsons.
     * if it swaps between i and grandson it will compare it to the new father to maintain the max-heap
     * property of the odd depth.
     * @param i index of the node to 'minHeapify' on
     */
    public void minHeapify(int i)
    {
        double temp = _heap[i];
        int minIndex = i;
        int right = right(i);
        int left = left(i);
        int rightRight = right(right);
        int leftRight = left(right);
        int rightLeft = right(left);
        int leftLeft = left(left);
        boolean grandChild = false;
        
        if (inRange(right) && _heap[right] < temp) 
        {
            minIndex = right;
            temp = _heap[right];
        }
        if (inRange(left) && _heap[left] < temp) 
        {
            minIndex = left;
            temp = _heap[left];
        }
        if (inRange(rightRight) && _heap[rightRight] < temp) 
        {
            minIndex = rightRight;
            temp = _heap[rightRight];
            grandChild = true;
        }
        if (inRange(leftRight) && _heap[leftRight] < temp) 
        {
            minIndex = leftRight;
            temp = _heap[leftRight];
            grandChild = true;
        }
        if (inRange(rightLeft) && _heap[rightLeft] < temp) 
        {
            minIndex = rightLeft;
            temp = _heap[rightLeft];
            grandChild = true;
        }
        if (inRange(leftLeft) && _heap[leftLeft] < temp) 
        {
            minIndex = leftLeft;
            temp = _heap[leftLeft];
            grandChild = true;
        }
    
        if (grandChild == true) // if min is a grandchild of i
        { 
            if (_heap[minIndex] < _heap[i])
            {
                this.swap(i, minIndex);
                if (_heap[minIndex] > _heap[parent(minIndex)]) // maintain the max heap property
                {
                    this.swap(minIndex, parent(minIndex));
                }
                minHeapify(minIndex);
            }
        } else if (_heap[minIndex] < _heap[i]) 
        {
            this.swap(minIndex, i);
        }
    }
    /**
     * simply determine if given node goes to min or max heapify method, even heightto max odd
     * height to min.
     * @param i index of the node of choice
     */
    public void heapify(int i)
    {
        int height = height(i);
        if (height % 2 == 0)// even height - max-min heap
        {
            this.maxHeapify(i);
        }
        else
            this.minHeapify(i);// odd height - min-max heap
    }
    /**
     * buildHeap 'cuts' the heap right at the last node to have sons, then sends all the nodes from
     * this point down to heapify method. going through this half of the heap will end up creating
     * a proper max-min heap.
     */
    public void buildHeap() 
    {
       for (int i = (getSize()/2); i > 0; i--)
       {
           heapify(i);
       }
    }
    /**
     * gets the index of the biggest member
     * @return 1
     */
        public int getMaxIndex()
    {
        if (_heapSize<1)
            System.exit(0);
        return 1;
    }
    /**
     * gets the index of the smallest member
     * @return the index of the min value of the heap
     */
    public int getMinIndex()
    {
        switch (_heapSize)
        {
            case 0 : return 0;
            case 1 : return 1;
            case 2 : return 2;
            default : return (getLeft(1)<getRight(1)) ? left(1) : right(1);
        }
    }
    /**
     * gets the value of the biggest member
     * @return the max value of the heap
     */
    public double getMax()
    {
       return _heap[1];
    }
    /**
     * gets the value of the smallest member
     * @return the min value of the heap
     */
    public double getMin()
    {
        return _heap[getMinIndex()];
    }
    /**
     * removing the max value and sorting the heap again
     * @return the biggest member that was just removed from the heap
     */
    public double extractMax()
    {
        double max = getMax();
        swap(1,_heapSize);
        _heap[_heapSize] = 0;
        setSize(getSize()-1);
        maxHeapify(1);
        return max;
    }
    /**
     * removing the min value and sorting the heap again
     * @return the smallest member that was just removed from the heap 
     */
    public double extractMin()
    {
        double min = getMin();
        int minIndex = getMinIndex();
        swap(minIndex, getSize());
        _heap[_heapSize] = 0;
        setSize(getSize()-1);
        minHeapify(minIndex);
        return min;
    }
    /**
     * delete a memeber of the heap and then sort the heap
     * @param i index of the member to remove from heap
     */
    public void delete(int i)
    {
        if(inRange(i))
        {
            swap(i,getSize());
            _heap[getSize()] = 0;
            setSize(getSize()-1);
            pushUp(_heapSize);
        }
    }
    /**
     * adds a new member to the heap
     * @param key the value to insert
     */
    public void insert(double key)
    {
      if (getSize()<MAX_SIZE)
      {
          setSize(getSize()+1);
          _heap[getSize()] = key;
          pushUp(_heapSize);
      }
    }
    /**
     * check if an index is within bounds of the heap
     * @param i index to check
     */
    public boolean inRange(int i)
    {
        return (i>0 && i <= _heapSize);
    }
    /**
     * push up check if the node is in a max or min depth, acording to that it checks if greater or smaller than it parent and send it to pushUpMax or pushUpMin respectfully
     * @param i index of the node to push up
     */
    public void pushUp(int i)
    {
        if (height(i) % 2 == 0) // if at max level
        {
            if (_heap[i] < _heap[parent(i)]) // and new member is smaller than parent
            {
                swap(i,parent(i)); // swap them to keep the property
                pushUpMin(parent(i)); //push up the new memeber at his new index (min depth)
            }
            else
            pushUpMax(i);
        }
        else if ((height(i) % 2) != 0)// at min level
        {
            if (_heap[i] > _heap[parent(i)]) // and new member is bigger than parent
            {
                swap(i,parent(i)); // swap them to keep the property
                pushUpMax(parent(i)); //push up new member at his new index (max depth)
            }
            else
            pushUpMin(i);
        }
    }
    /**
     * push up max is used to insert new members to the heap, it compares it from the bottom to the top in a build already max-min heap
     * @param i index of node to push up through max levels
     */
    public void pushUpMax(int i)
    {
      if (grandParent(i) < 1){
          return;}
      if (_heap[i] > _heap [grandParent(i)])
      {
          swap(i,grandParent(i));
          pushUpMax(grandParent(i));
      }
    }
    /**
     * push up min is used to insert new members to the heap, it compares it from the bottom to the top in a build already max-min heap
     * @param i index of node to push up through min levels
     */
    public void pushUpMin(int i)
    {
      if (grandParent(i) < 1){
          return;}
        if (_heap[i] < _heap [grandParent(i)])
      {
          swap(i,grandParent(i));
          pushUpMin(grandParent(i));
      }
    }
    /**
     * print out the heap
     */
    public String toString()
    {
        String str = "";
        if (getSize()>0)
        {
            for (int i=1; i<=getSize(); i++)
            {
                double val = getVal(i);
                str = str + val;
                if (i==getSize())
                {
                    str = str + ".";
                    if (((i-1)%10 == 0) && i != 1){
                    str = str +"\n";}
                }
                else
                    str = str + ", ";
                if (((i-1)%10 == 0) && i != 1){
                    str = str +"\n";}
            }
        }
        return str;
    }
}