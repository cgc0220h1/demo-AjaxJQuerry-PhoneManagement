package api;

import javassist.tools.web.BadHttpRequest;
import model.SmartPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.phone.SmartPhoneService;
import service.phone.exception.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/phones")
public class SmartPhoneAPI {
    private final SmartPhoneService smartPhoneService;

    @Autowired
    public SmartPhoneAPI(SmartPhoneService smartPhoneService) {
        this.smartPhoneService = smartPhoneService;
    }

    @GetMapping()
    public ResponseEntity<List<SmartPhone>> getAllPhones(@PageableDefault(size = 5, sort = "id") Pageable pageable) throws NotFoundException {
        Page<SmartPhone> phonePage = smartPhoneService.findAll(pageable);
        return new ResponseEntity<>(phonePage.getContent(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmartPhone> getOne(@PathVariable("id") Long id) throws NotFoundException {
        SmartPhone smartPhone = smartPhoneService.findOne(id);
        return new ResponseEntity<>(smartPhone, HttpStatus.FOUND);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SmartPhone createOne(@RequestBody SmartPhone smartPhone) throws BadHttpRequest {
        if (smartPhone.getId() != null) {
            throw new BadHttpRequest();
        }
        return smartPhoneService.save(smartPhone);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SmartPhone updateOne(@RequestBody SmartPhone smartPhone) throws NotFoundException, InvalidDataAccessApiUsageException {
        SmartPhone phoneFound = smartPhoneService.findOne(smartPhone.getId());
        phoneFound.setBrand(smartPhone.getBrand());
        phoneFound.setModel(smartPhone.getBrand());
        phoneFound.setPrice(smartPhone.getPrice());
        return smartPhoneService.save(phoneFound);
    }

    @DeleteMapping(value = {"/{id}/delete", "/delete/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteOne(@PathVariable("id") Long id) throws NotFoundException, InvalidDataAccessApiUsageException {
        SmartPhone phoneFound = smartPhoneService.findOne(id);
        smartPhoneService.delete(phoneFound);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundException() {
    }

    @ExceptionHandler({BadHttpRequest.class, InvalidDataAccessApiUsageException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleBadRequest() {
    }
}
