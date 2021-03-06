package ru.coutvv.vkliker.core.api.storage.post;

import ru.coutvv.vkliker.core.api.entity.post.Post;
import ru.coutvv.vkliker.core.api.entity.post.impl.WallPost;
import ru.coutvv.vkliker.core.api.storage.PostSource;
import ru.coutvv.vkliker.core.api.support.ScriptExecutor;
import ru.coutvv.vkliker.core.api.support.raw.Json;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author coutvv    16.04.2018
 */
public class VkWall implements PostSource {

	private final ScriptExecutor executor;
	private final Integer ownerId;

	public VkWall(ScriptExecutor executor, Integer ownerId) {
		this.executor = executor;
		this.ownerId = ownerId;

	}

	private static final String SCRIPT_TEMPLATE = "return API.wall.get({\"owner_id\"  : %1$s, \"count\" : %2$d, \"filter\" : \"owner\", \"offset\" : %3$d});";

	@Override
	public List<Post> posts(int count, int offset) throws Exception {
		return rawPosts(count, offset).stream().map(WallPost::new).collect(Collectors.toList());
	}

	@Override
	public List<Json> rawPosts(int count, int offset) throws Exception {
		return executor.raw(
				String.format(SCRIPT_TEMPLATE, ownerId, count, offset)
		).arrField("items");
	}

	@Override
	public int quantityPosts() throws Exception {
		return executor.raw(
				String.format(SCRIPT_TEMPLATE, ownerId, 1, 0)
		).longField("count").intValue();
	}
}
