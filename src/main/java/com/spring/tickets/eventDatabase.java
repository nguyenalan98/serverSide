package com.spring.tickets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class eventDatabase implements CommandLineRunner {

	@Autowired
	private EventRepository eventRepository;

	@SuppressWarnings("null")
	@Override
	public void run(String... args) throws Exception {
		
		URL url = new URL("http://localhost:3000/events");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		con.disconnect();
		
		String[] splitter = content.toString().split("},");
		JSONArray array = new JSONArray();
		List<Event> eventsList = null;
		for(String x : splitter) {
			JSONObject json = new JSONObject(x.substring(1) + "}");
			int id = json.getInt("id");
			String name = json.getString("name");
			String date = json.getString("date");
			String time = json.getString("time");
			String duration = json.getString("duration");
			double price = json.getDouble("price");
			String status = json.getString("status");
			array.put(json);
			Event event = new Event(id,name,date,time,duration,price,status);
			eventsList.add(event);
		}
		eventRepository.saveAll(eventsList);
	}

}
