package cpre419.lab05;

import java.util.List;

/**
 * @author Andrew
 */
public class MinimalTweet {

    public Entity entities;
    public User user;

    class Entity {
        public List<HashTag> hashtags;

        public Entity() {}
    }

    class HashTag {
        public String text;

        public HashTag() {}

        @Override
        public String toString() {
            return text;
        }
    }

    class User {
        public int followers_count;
        public String screen_name;
        public int statuses_count;

        public User() {}
    }
}
