package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.course.CourseRequest;
import peaksoft.dto.course.CourseResponse;
import peaksoft.dto.course.CourseResponseView;
import peaksoft.entity.Course;
import peaksoft.service.CourseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@PreAuthorize("hasAnyAuthority('ADMIN','EDITOR')")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public CourseResponse create(@RequestBody CourseRequest request) {
        return courseService.create(request);
    }

    @PutMapping("{id}")
    public CourseResponse update(@PathVariable long id,
                                  @RequestBody CourseRequest request) {
        return courseService.update(id, request);
    }

    @GetMapping(("{id}"))
    public CourseResponse getById(@PathVariable long id) {
        return courseService.getById(id);
    }

    @DeleteMapping("{id}")
    public CourseResponse delete(@PathVariable long id) {
        return courseService.delete(id);
    }

//    @GetMapping
//    public List<Course> getAllCourses() {
//        return courseService.getAll();
//    }

    @GetMapping
    public CourseResponseView getAllCourses(@RequestParam(name = "name",required = false)String name,
                                             @RequestParam int page,
                                             @RequestParam int size){
        return courseService.getAllCourses(name,page-1,size);
    }
}
