package tickets;

import java.util.Objects;

public class Ticket {
    private String queue ;
    private String title ;
    private String priority;
    private String submitterEmail;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public void setSubmitterEmail(String submitterEmail) {
        this.submitterEmail = submitterEmail;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSubmitterEmail() {
        return submitterEmail;
    }

    public String getPriority() {
        return priority;
    }

    public String getQueue() {
        return queue;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "queue='" + queue + '\'' +
                ", title='" + title + '\'' +
                ", priority='" + priority + '\'' +
                ", submitterEmail='" + submitterEmail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(queue, ticket.queue) &&
                Objects.equals(title, ticket.title) &&
                Objects.equals(priority, ticket.priority) &&
                Objects.equals(submitterEmail, ticket.submitterEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queue, title, priority, submitterEmail);
    }
}
