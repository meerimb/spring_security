package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.dto.student.StudentResponseView;
import peaksoft.dto.teacher.TeacherRequest;
import peaksoft.dto.teacher.TeacherResponse;
import peaksoft.dto.teacher.TeacherResponseView;
import peaksoft.entity.Course;
import peaksoft.entity.Student;
import peaksoft.entity.Teacher;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository repository;
    private final CourseRepository courseRepository;

    public TeacherResponse create(TeacherRequest request){
        Teacher teacher=mapToEntity(request);
        repository.save(teacher);
        return mapToResponse(teacher);
    }

    public TeacherResponse update(long id, TeacherRequest request) {
        Optional<Teacher> teacher = repository.findById(id);
        if (teacher.isEmpty()) {
            System.out.println("teacher is not found");
        }
        mapToUpdate(teacher.get(), request);
        return mapToResponse(repository.save(teacher.get()));

    }

    public TeacherResponse getById(long id) {

        Optional<Teacher> teacher = repository.findById(id);
        if (teacher.isEmpty()) {
            System.out.println("teacher is not found");
        }
        return mapToResponse(repository.findById(id).get());
    }

    public TeacherResponse delete(long id) {
        Teacher teacher = repository.findById(id).get();
        repository.deleteById(id);
        return mapToResponse(teacher);
    }

//    public List<Teacher> getAll() {
//        return repository.findAll();
//    }

    public Teacher mapToEntity(TeacherRequest request){
        Teacher teacher=new Teacher();
        teacher.setFirstname(request.getFirstname());
        teacher.setLastname(request.getLastname());
        teacher.setEmail(request.getEmail());
        Course course=courseRepository.findById(request.getCourseId()).get();
        course.setId(request.getCourseId());
        teacher.setCourse(course);
        return teacher;

    }

    public TeacherResponse mapToResponse(Teacher teacher){
        return TeacherResponse.builder()
                .firstname(teacher.getFirstname())
                .lastname(teacher.getLastname())
                .email(teacher.getEmail())
                .id(teacher.getId())
                .course(teacher.getCourse())
                .build();

    }

    public Teacher mapToUpdate(Teacher teacher, TeacherRequest request) {
        List<Teacher> teachers = new ArrayList<>();
        teacher.setFirstname(request.getFirstname());
        teacher.setLastname(request.getLastname());
        teacher.setEmail(request.getEmail());
        Course course=courseRepository.findById(request.getCourseId()).get();
        teacher.setCourse(course);
        return teacher;
    }

    public List<TeacherResponse> map(List<Teacher> teachers) {
        List<TeacherResponse> responses = new ArrayList<>();
        for (Teacher teacher:teachers) {
            responses.add(mapToResponse(teacher));
        }
        return responses;
    }

    public TeacherResponseView getAllTeachers(String name, int page, int size){
        TeacherResponseView teacherResponseView=new TeacherResponseView();
        Pageable pageable= PageRequest.of(page, size);
        teacherResponseView.setResponses(map(search(name,pageable)));
        return teacherResponseView;

    }

    List<Teacher> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return repository.searchAndPagination(text.toUpperCase(), pageable);
    }
}


