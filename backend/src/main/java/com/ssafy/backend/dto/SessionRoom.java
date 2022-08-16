package com.ssafy.backend.dto;

//import com.ssafy.backend.db.entity.Room;
import io.openvidu.java.client.Session;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionRoom {
    private Session sessionCamera;
    private Session sessionScreen;
    private Room room;
    private String Host;
}
