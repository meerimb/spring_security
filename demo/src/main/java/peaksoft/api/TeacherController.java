package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.teacher.TeacherRequest;
import peaksoft.dto.teacher.TeacherResponse;
import peaksoft.dto.teacher.TeacherResponseView;
import peaksoft.entity.Teacher;
import peaksoft.service.TeacherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
@PreAuthorize("hasAnyAuthority('ADMIN','EDITOR')")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public TeacherResponse create(@RequestBody TeacherRequest request) {
        return teacherService.create(request);
    }

    @PutMapping("{id}")
    public TeacherResponse update(@PathVariable long id,
                                @RequestBody TeacherRequest request) {
        return teacherService.update(id, request);
    }

    @GetMapping(("{id}"))
    public TeacherResponse getById(@PathVariable long id) {
        return teacherService.getById(id);
    }

    @DeleteMapping("{id}")
    public TeacherResponse delete(@PathVariable long id) {
        return teacherService.delete(id);
    }

//    @GetMapping
//    public List<Teacher> getAllGroups() {
//        return teacherService.getAll();
//    }

    @GetMapping
    public TeacherResponseView getAllTeachers(@RequestParam(name = "name",required = false)String name,
                                              @RequestParam int page,
                                              @RequestParam int size){
        return teacherService.getAllTeachers(name,page-1,size);
    }
}


