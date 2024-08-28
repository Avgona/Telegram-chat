export class ChatRoom {
    public chatName: string
    public description: string
    public isSubscribed: boolean


    constructor(chat: ChatRoom, isSubscribed: boolean) {
        this.chatName = chat.chatName;
        this.description = chat.description;
        this.isSubscribed = isSubscribed;
    }
}

export class ChatJoinRequest {
    public username: string;
    public chatName: string;
    public description: string;

    constructor(username: string, chatName: string, description: string) {
        this.username = username
        this.chatName = chatName;
        this.description = description;
    }
}