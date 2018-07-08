package com.sjtubus.model.response;

import com.sjtubus.model.BusMessage;

import java.util.List;

public class MessageResponse extends HttpResponse {
    private List<BusMessage> messages;

    public List<BusMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<BusMessage> messages) {
        this.messages = messages;
    }
}
