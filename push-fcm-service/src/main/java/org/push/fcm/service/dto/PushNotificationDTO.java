package org.push.fcm.service.dto;

import com.google.firebase.messaging.MulticastMessage;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PushNotificationDTO {

    private String title;
    private String content;
    private String imgUrl;
    private String topic;
    private String collapseKey;
    private List<String> members;


    public MulticastMessage buildMultiMessage(PushNotificationDTO pushNotification) {
        return MulticastMessage.builder()
                .putAllData(pushNotification.makePutData(pushNotification))
                .setAndroidConfig()
                .build();
    }

    public Map<String, String> makePutData(PushNotificationDTO request) {

        Map<String, String> putData = new HashMap<>();
        if(!ObjectUtils.isEmpty(request.getTitle())) putData.put("pushTitle", request.getTitle());
        if(!ObjectUtils.isEmpty(request.getContent())) putData.put("pushBody", request.getContent());
        if(!ObjectUtils.isEmpty(request.getImgUrl())) putData.put("pushImg", request.getImgUrl());

        return putData;
    }
}
