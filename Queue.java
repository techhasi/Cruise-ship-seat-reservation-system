public class Queue {
    private int front, end;
    private Passenger [] newQueue = new Passenger[5];        //Creating an array of Passengers for queue

    public Queue() {
        this.front = this.end = -1;     //Initializing front and rear into -1
    }

    public void enqueue (Passenger info) {
        if (isEmpty())          //Checks if the queue is empty
            front ++;
        end = (end+1) % newQueue.length;
        newQueue[end] = info;
    }

    //Check whether the queue is empty
    public boolean isEmpty () {
        return front == -1;
    }

    //Check if the queue is full
    public boolean isFull () {
        return (end+1) % newQueue.length == front;
    }

    //Returns Passenger object of the first in queue
    public Passenger dequeue () {
        Passenger temp = newQueue[front];
        newQueue[front] = null;

        if (front == end)
            front = end -1;
        else
            front = (front+1) % newQueue.length;
        return temp;
    }

}
