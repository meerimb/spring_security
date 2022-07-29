package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.group.GroupRequest;
import peaksoft.dto.group.GroupResponse;
import peaksoft.dto.group.GroupResponseView;
import peaksoft.entity.Group;
import peaksoft.service.GroupService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
@PreAuthorize("hasAnyAuthority('ADMIN','EDITOR')")
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public GroupResponse create(@RequestBody GroupRequest request) {
        return groupService.create(request);
    }

    @PutMapping("{id}")
    public GroupResponse update(@PathVariable long id,
                                 @RequestBody GroupRequest request) {
        return groupService.update(id, request);
    }

    @GetMapping(("{id}"))
    public GroupResponse getById(@PathVariable long id) {
        return groupService.getById(id);
    }

    @DeleteMapping("{id}")
    public GroupResponse delete(@PathVariable long id) {
        return groupService.delete(id);
    }

//    @GetMapping
//    public List<Group> getAllGroups() {
//        return groupService.getAll();
//    }

    @GetMapping
    public GroupResponseView getAllGroups(@RequestParam(name = "name",required = false)String name,
                                          @RequestParam int page,
                                          @RequestParam int size){
        return groupService.getAllGroups(name,page-1,size);
    }
}


