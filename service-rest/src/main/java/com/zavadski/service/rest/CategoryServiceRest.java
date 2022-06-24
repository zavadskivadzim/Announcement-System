package com.zavadski.service.rest;

import com.zavadski.model.Category;
import com.zavadski.service.CategoryService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceRest implements CategoryService {

    private String url;

    private RestTemplate restTemplate;

    public CategoryServiceRest() {
    }

    public CategoryServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Category> findAllCategories() {

        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Category>) responseEntity.getBody();
    }

    @Override
    public Category createCategory(Category category) {

        ResponseEntity responseEntity = restTemplate.postForEntity(url, category, Category.class);
        return (Category) responseEntity.getBody();
    }

    @Override
    public Category findCategoryById(UUID id) {

        ResponseEntity<Category> responseEntity =
                restTemplate.getForEntity(url + "/" + id, Category.class);
        return responseEntity.getBody();
    }

    @Override
    public Category updateCategory(Category category) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Category> entity = new HttpEntity<>(category, headers);
        ResponseEntity<Category> result = restTemplate.exchange(
                url, HttpMethod.PUT, entity, Category.class);
        return result.getBody();
    }

    @Override
    public void deleteCategory(UUID id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Category> entity = new HttpEntity<>(headers);
        ResponseEntity<Integer> result =
                restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, entity, Integer.class);
        return result.getBody();
    }

    @Override
    public boolean checkCategoryOnUnique(String categoryName) {
        Boolean result = restTemplate.getForObject(url + "/unique/" + categoryName, Boolean.class);
        return result;
    }

}
