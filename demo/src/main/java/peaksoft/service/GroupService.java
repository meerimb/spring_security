package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.dto.group.GroupRequest;
import peaksoft.dto.group.GroupResponse;
import peaksoft.dto.group.GroupResponseView;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.GroupRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository repository;
    private final CourseRepository courseRepository;


    public GroupResponse create(GroupRequest request) {
        Group group = mapToEntity(request);
        repository.save(group);
        return mapToResponse(group);
    }

    public GroupResponse update(long id, GroupRequest request) {
        Optional<Group> group = repository.findById(id);
        if (group.isEmpty()) {
            System.out.println("group is not found");
        }
        mapToUpdate(group.get(), request);
        return mapToResponse(repository.save(group.get()));

    }

    public GroupResponse getById(long id) {

        Optional<Group> group = repository.findById(id);
        if (group.isEmpty()) {
            System.out.println("group is not found");
        }
        return mapToResponse(repository.findById(id).get());
    }

    public GroupResponse delete(long id) {
        Group group = repository.findById(id).get();
        repository.deleteById(id);
        return mapToResponse(group);
    }

//    public List<Group> getAll() {
//        return repository.findAll();
//    }

    public Group mapToEntity(GroupRequest groupRequest){
        List<Course> courseList = new ArrayList<>();
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setDateOfStart(groupRequest.getDateOfStart());
        group.setDateOfFinish(groupRequest.getDateOfFinish());
        Course course = courseRepository.findById(groupRequest.getCourse()).get();
        courseList.add(course);
        group.setCourses(courseList);
        return group;
    }

    public Group mapToUpdate(Group group, GroupRequest groupRequest) {
        List<Course> courses = new ArrayList<>();
        group.setGroupName(group.getGroupName());
        group.setDateOfStart(group.getDateOfStart());
        group.setDateOfFinish(group.getDateOfFinish());

        Course course = courseRepository.findById(groupRequest.getCourse()).get();
        courses.add(course);
        group.setCourses(courses);
        return group;
    }

    public GroupResponse mapToResponse(Group group){
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(group.getId());
        groupResponse.setGroupName(group.getGroupName());
        groupResponse.setDateOfStart(group.getDateOfStart());
        groupResponse.setDateOfFinish(group.getDateOfFinish());
        groupResponse.setCourses(group.getCourses());
        return groupResponse;
    }

    public List<GroupResponse> map(List<Group> groups) {
        List<GroupResponse> responses = new ArrayList<>();
        for (Group group : groups) {
            responses.add(mapToResponse(group));
        }
        return responses;
    }

//    public GroupResponseView getAllGroups(String name, LocalDate date, int page, int size){
//        GroupResponseView groupResponseView=new GroupResponseView();
//        Pageable pageable= PageRequest.of(page, size);
//        groupResponseView.setResponses(map(search(name,pageable)));
//        return groupResponseView;
//    }

    public GroupResponseView getAllGroups(String name, int page, int size){
        GroupResponseView groupResponseView=new GroupResponseView();
        Pageable pageable= PageRequest.of(page, size);
        groupResponseView.setResponses(map(search(name,pageable)));
        return groupResponseView;

    }

    List<Group> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return repository.searchAndPagination(text.toUpperCase(), pageable);
    }
}