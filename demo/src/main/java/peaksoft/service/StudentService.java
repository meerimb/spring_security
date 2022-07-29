package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.dto.student.StudentRequest;
import peaksoft.dto.student.StudentResponse;
import peaksoft.dto.student.StudentResponseView;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.repository.GroupRepository;
import peaksoft.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final GroupRepository groupRepository;

    public StudentResponse create(StudentRequest request){
        Student student=mapToEntity(request);
        repository.save(student);
        return mapToResponse(student);
    }

    public StudentResponse update(long id, StudentRequest request) {
        Optional<Student> student = repository.findById(id);
        if (student.isEmpty()) {
            System.out.println("student is not found");
        }
        mapToUpdate(student.get(), request);
        return mapToResponse(repository.save(student.get()));

    }

    public StudentResponse getById(long id) {

        Optional<Student> course = repository.findById(id);
        if (course.isEmpty()) {
            System.out.println("student is not found");
        }
        return mapToResponse(repository.findById(id).get());
    }

    public StudentResponse delete(long id) {
        Student student = repository.findById(id).get();
        repository.deleteById(id);
        return mapToResponse(student);
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student mapToEntity(StudentRequest request){
        Student student=new Student();
        student.setFirstname(request.getFirstname());
        student.setLastname(request.getLastname());
        student.setEmail(request.getEmail());
        student.setStudyFormat(request.getStudyFormat());
        Group group=groupRepository.findById(request.getGroupId()).get();
        student.setGroup(group);
        return student;

    }

    public StudentResponse mapToResponse(Student student){
        return StudentResponse.builder()
                .firstname(student.getFirstname())
                .lastname(student.getLastname())
                .email(student.getEmail())
                .studyFormat(student.getStudyFormat())
                .id(student.getId())
                .group(student.getGroup())
                .build();

    }

        public Student mapToUpdate(Student student, StudentRequest request) {
        List<Student> students = new ArrayList<>();
        student.setFirstname(request.getFirstname());
        student.setLastname(request.getLastname());
        student.setEmail(request.getEmail());
        student.setStudyFormat(request.getStudyFormat());
        Group group=groupRepository.findById(request.getGroupId()).get();
        student.setGroup(group);
        return student;
    }

    public List<StudentResponse> map(List<Student> students) {
        List<StudentResponse> responses = new ArrayList<>();
        for (Student student:students) {
            responses.add(mapToResponse(student));
        }
        return responses;
    }

    public StudentResponseView getAllStudents(String name, int page, int size){
        StudentResponseView studentResponseView=new StudentResponseView();
        Pageable pageable= PageRequest.of(page, size);
        studentResponseView.setResponses(map(search(name,pageable)));
        return studentResponseView;

    }

    List<Student> search(String name,Pageable pageable) {
        String text = name == null ? "" : name;
        return repository.searchAndPagination(text.toUpperCase(), pageable);
    }

}


