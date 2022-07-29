package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.student.StudentRequest;
import peaksoft.dto.student.StudentResponse;
import peaksoft.dto.student.StudentResponseView;
import peaksoft.entity.Student;;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
@PreAuthorize("hasAnyAuthority('ADMIN','EDITOR')")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public StudentResponse create(@RequestBody StudentRequest request) {
        return studentService.create(request);
    }

    @PutMapping("{id}")
    public StudentResponse update(@PathVariable long id,
                                 @RequestBody StudentRequest request) {
        return studentService.update(id, request);
    }

    @GetMapping(("{id}"))
    public StudentResponse getById(@PathVariable long id) {
        return studentService.getById(id);
    }

    @DeleteMapping("{id}")
    public StudentResponse delete(@PathVariable long id) {
        return studentService.delete(id);
    }

//    @GetMapping
//    public List<Student> getAllStudents() {
//        return studentService.getAll();
//    }

    @GetMapping
    public StudentResponseView getAllStudents(@RequestParam(name = "name",required = false)String name,
                                              @RequestParam int page,
                                              @RequestParam int size){
        return studentService.getAllStudents(name,page-1,size);
    }
}


