package com.spring.tickets;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class eventController {
	private final EventRepository repository;

	eventController(EventRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/events")
	List<Event> all() {
		return repository.findAll();
	}

	@GetMapping("/events/{id}")
	Event one(@PathVariable Long id) throws NotFoundException {
		return repository.findById(id).orElseThrow(() -> new NotFoundException());
	}

	@PostMapping("/events")
	Event newEvent(@RequestBody Event newEvent) {
		return repository.save(newEvent);
	}

	@PutMapping("/events/{id}")
	Event replaceEmployee(@RequestBody Event newEvent, @PathVariable Long id) {

		return repository.findById(id).map(event -> {
			event.name = (newEvent.name);
			event.date = (newEvent.date);
			event.time = (newEvent.time);
			event.duration = (newEvent.duration);
			event.price = (newEvent.price);
			return repository.save(event);
		}).orElseGet(() -> {
			newEvent.setID(id);
			return repository.save(newEvent);
		});
	}

	@DeleteMapping("/events/{id}")
	void deleteEvent(@PathVariable Long id) {
		repository.deleteById(id);
	}

}