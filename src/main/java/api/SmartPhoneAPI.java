package api;

import model.SmartPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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

//    @GetMapping("")
//    public ResponseEntity<List<SmartPhone>> getAllPhones() throws NotFoundException {
//        List<SmartPhone> list = smartPhoneService.findAll();
//        return new ResponseEntity<>(list, HttpStatus.FOUND);
//    }

    @GetMapping()
    public ResponseEntity<List<SmartPhone>> getAllPhones(@PageableDefault(size = 5, sort = "id") Pageable pageable) throws NotFoundException {
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
//        Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("model").descending());
        Page<SmartPhone> phonePage = smartPhoneService.findAll(pageable);
        return new ResponseEntity<>(phonePage.getContent(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmartPhone> getOne(@PathVariable("id") Long id) throws NotFoundException {
        SmartPhone smartPhone = smartPhoneService.findOne(id);
        return new ResponseEntity<>(smartPhone, HttpStatus.FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundException() {
    }
}
