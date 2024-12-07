package APP;

// Linked list structure to manage daily logs

public class LogLinkedList {
    private LogNode head; // Head of the linked list.

    // Add new log at the end of linked list
    public void addLog(DailyLog log) {
        LogNode newNode = new LogNode(log);
        if (head == null) {
            head = newNode; // If list = empty,  new node = head
        } else {
            LogNode current = head;
            while (current.next != null) {
                current = current.next; // Traverse to end of the list
            }
            current.next = newNode; // Append new node at the end
        }
    }

    // Traverse linked list and print each log
    public void traverse() {
        LogNode current = head;
        while (current != null) {
            System.out.println(current.log);
            current = current.next;
        }
    }

    // Node structure for linked list
    static class LogNode {
        DailyLog log;   // Daily log stored in node
        LogNode next;   // Reference to next node in the list

        // LogNode Constructor (initialize node with daily log)
        public LogNode(DailyLog log) {
            this.log = log;
            this.next = null;
        }
    }
}
