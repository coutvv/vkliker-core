package ru.coutvv.vkliker.core.vkapi.posts;


import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import ru.coutvv.vkliker.core.vkapi.posts.entity.Post;

import java.time.LocalDate;
import java.util.List;

/**
 * @author coutvv
 */
public interface Wall {

    /**
     * Скрипт получения постов со стены
     */
    String GET_SCRIPT_TEMPLATE = "return API.wall.get({\"owner_id\"  : %1$s, \"count\" : %2$d, \"filter\" : \"owner\", \"offset\" : %3$d});";


    /**
     * update
     */
    List<Post> refresh() throws ClientException, ApiException;

    /**
     * Get all cache posts
     *
     * @return
     * @throws ClientException
     * @throws ApiException
     */
    List<Post> getAll() throws ClientException, ApiException;

    /**
     * Get all posts since target date
     */
    List<Post> getPostsSince(LocalDate date);


    /**
     * Get all post between two dates
     */
    List<Post> getPostsBetween(LocalDate begin, LocalDate end);

    /**
     * Get last some posts
     */
    List<Post> getPosts(int count);

}