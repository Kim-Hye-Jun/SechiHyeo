package com.ssafy.backend;

import com.ssafy.backend.api.service.RoomService;
import com.ssafy.backend.api.service.RoomServiceImpl;
import com.ssafy.backend.dto.request.RoomCreateReq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

//		RoomService roomService = new RoomServiceImpl("MY_SECRET", "https://i7a508.p.ssafy.io:8443/");


//		roomService.sendSignal(openviduid);
	}

}
