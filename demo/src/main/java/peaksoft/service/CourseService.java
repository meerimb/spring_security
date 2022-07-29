package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.dto.course.CourseRequest;
import peaksoft.dto.course.CourseResponse;
import peaksoft.dto.course.CourseResponseView;
import peaksoft.dto.student.StudentResponseView;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Student;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final CompanyRepository companyRepository;

    public CourseResponse create(CourseRequest request){
        Course course=mapToEntity(request);
        repository.save(course);
        return mapToResponse(course);
    }

    public CourseResponse update(long id, CourseRequest request) {
        Optional<Course> course = repository.findById(id);
        if (course.isEmpty()) {
            System.out.println("course is not found");
        }
        mapToUpdate(course.get(), request);
        return mapToResponse(repository.save(course.get()));

    }

    public CourseResponse getById(long id) {

        Optional<Course> course = repository.findById(id);
        if (course.isEmpty()) {
            System.out.println("course is not found");
        }
        return mapToResponse(repository.findById(id).get());
    }

    public CourseResponse delete(long id) {
        Course course = repository.findById(id).get();
        repository.deleteById(id);
        return mapToResponse(course);
    }

//    public List<Course> getAll() {
//        return repository.findAll();
//    }

    public Course mapToEntity(CourseRequest request){
        Course course=new Course();
        course.setCourseName(request.getCourseName());
        course.setDuration(request.getDuration());
        Company company=companyRepository.findById(request.getCompanyId()).get();
        course.setCompany(company);
        return course;

    }

    public CourseResponse mapToResponse(Course course){
        return CourseResponse.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .duration(course.getDuration())
                .companyId(course.getCompany().getId())
                .build();

    }

    public Course mapToUpdate(Course course, CourseRequest request) {
        List<Course> courses = new ArrayList<>();
        course.setCourseName(request.getCourseName());
        course.setDuration(request.getDuration());
        Company company=companyRepository.findById(request.getCompanyId()).get();
        course.setCompany(company);
        return course;
    }

    public List<CourseResponse> map(List<Course> courses) {
        List<CourseResponse> responses = new ArrayList<>();
        for (Course course:courses) {
            responses.add(mapToResponse(course));
        }
        return responses;
    }

    public CourseResponseView getAllCourses(String name, int page, int size){
        CourseResponseView courseResponseView=new CourseResponseView();
        Pageable pageable= PageRequest.of(page, size);
        courseResponseView.setResponses(map(search(name,pageable)));
        return courseResponseView;

    }

    List<Course> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return repository.searchAndPagination(text.toUpperCase(), pageable);
    }
}
