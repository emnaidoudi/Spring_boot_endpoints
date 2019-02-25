package com.chatboty.chatboty.controllers;

import com.chatboty.chatboty.models.Intent;
import com.chatboty.chatboty.repositories.IntentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/intents")
public class IntentController {
    @Autowired
    private IntentRepository repository;

    @GetMapping(value = "/")
    public ResponseEntity<List<Intent>> getAllIntents() {
        return new ResponseEntity<List<Intent>>(repository.findAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/{tag}")
    public ResponseEntity<Intent> getPetByTag(@PathVariable("tag") String tag) {
        if(repository.findByTag(tag)==null){
            return  new ResponseEntity("Tag dosn't exist !",
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Intent>(repository.findByTag(tag),HttpStatus.OK);
    }
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json" )
    public ResponseEntity<Intent> addIntent(@Valid @RequestBody Intent intent){
        if (repository.existsByTag(intent.getTag())){
            return new ResponseEntity("Tag already exists. Tag must be unique !",
                    HttpStatus.NOT_ACCEPTABLE);
        }
        intent.set_id(ObjectId.get());
        repository.save(intent);
        return new ResponseEntity<Intent>(intent,HttpStatus.OK);
    }
    @PutMapping(value = "/{tag}",consumes = "application/json")
    public ResponseEntity updateIntent(@PathVariable("tag") String tag, @Valid @RequestBody Intent intent){
        Intent formerIntent= repository.findByTag(tag);
        intent.setTag(tag);
        intent.set_id(ObjectId.get());
        repository.delete(formerIntent);
        repository.save(intent);
        return new  ResponseEntity("Updated succefully !",
                HttpStatus.OK);
    }
    @DeleteMapping(value = "/{tag}")
    public ResponseEntity deleteIntent(@PathVariable String tag) {
        repository.delete(repository.findByTag(tag));
        return  new ResponseEntity("Deleted succefullly",
                HttpStatus.OK);
    }

}
