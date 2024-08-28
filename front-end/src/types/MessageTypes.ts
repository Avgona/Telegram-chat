export class MessageItem {
    public messageId: string | null;
    public author: string;
    public content: string;
    public sentAt: string;

    constructor(messageId: string | null, author: string, content: string, sentAt: Date) {
        this.messageId = messageId
        this.author = author;
        this.content = content;
        this.sentAt = sentAt.toISOString();
    }
}