package crud_Three.crud_Three.Controller;

import crud_Three.crud_Three.Model.ListModel;
import crud_Three.crud_Three.Repo.ListRepo;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/List")
public class ListController {
    ListRepo listRepo;

    ListController(ListRepo listRepo) {
        this.listRepo = listRepo;
    }

    //GET All
    @GetMapping("")
    List<ListModel> getAllList()
    {
        return listRepo.findAll();
    }

    //GET ONE
    @GetMapping("{id}")
    ResponseEntity<ListModel> getSingleList(@PathVariable Long id)
    {
            if (listRepo.findById(id).isPresent())
            {
                return new ResponseEntity<>(listRepo.findById(id).get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //PATCH
    @PatchMapping("{id}")
    ResponseEntity<ListModel> patchList(@PathVariable Long id, @RequestBody Map<String, String> body)
    {
            if (listRepo.findById(id).isPresent())
            {
                ListModel holder = listRepo.findById(id).get();

                for (Map.Entry<String, String> entry: body.entrySet())
                {
                    switch (entry.getKey())
                    {
                        case "Title" -> holder.setTitle(entry.getValue());
                        case "Content" -> holder.setContent(entry.getValue());
                    }
                }
                listRepo.save(holder);
                return new ResponseEntity<>(listRepo.findById(id).get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //POST
    @PostMapping("")
    ResponseEntity<ListModel> postList(@RequestBody ListModel body)
    {
           return new ResponseEntity<>(listRepo.save(body), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("{id}")
    ResponseEntity<ListModel> deleteList(@PathVariable Long id)
    {
        if (listRepo.findById(id).isPresent())
        {
            listRepo.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


}
