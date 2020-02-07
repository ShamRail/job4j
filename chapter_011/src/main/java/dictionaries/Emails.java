package dictionaries;

import java.util.*;

public class Emails {

    public static Map<String, Set<String>> handle(Map<String, Set<String>> input) {
        Map<String, Set<String>> output = new LinkedHashMap<>();
        for (Map.Entry<String, Set<String>> inEntry : input.entrySet()) {
            String mergeKey = null;
            Set<String> mergeValue = null;
            for (Map.Entry<String, Set<String>> outEntry : output.entrySet()) {
                for (String email : inEntry.getValue()) {
                    if (outEntry.getValue().contains(email)) {
                        mergeKey = outEntry.getKey();
                        mergeValue = input.get(inEntry.getKey());
                        break;
                    }
                }
                if (mergeKey != null) {
                    break;
                }
            }
            if (mergeKey != null) {
                output.get(mergeKey).addAll(mergeValue);
            } else {
                output.put(inEntry.getKey(), new LinkedHashSet<>(inEntry.getValue()));
            }
        }
        return output;
    }

    public static Map<String, Set<String>> handle2(Map<String, Set<String>> input) {
        Map<String, String> usedEmails = new HashMap<>();
        Map<String, Set<String>> output = new LinkedHashMap<>();
        for (Map.Entry<String, Set<String>> entry : input.entrySet()) {
            String user = entry.getKey();
            Set<String> userEmails = entry.getValue();
            String holdUser = null;
            for (String email : userEmails) {
                if (usedEmails.containsKey(email)) {
                    holdUser = usedEmails.get(email);
                    break;
                }
            }
            if (holdUser == null) {
                output.put(user, new LinkedHashSet<>(userEmails));
            } else {
                output.get(holdUser).addAll(userEmails);
                user = holdUser;
            }
            for (String email : userEmails) {
                usedEmails.putIfAbsent(email, user);
            }
        }
        return output;
    }

    public static Set<User> handle3(Set<User> input) {
        Set<User> components = new LinkedHashSet<>();
        Set<User> usedUsers = new HashSet<>();
        Set<Email> usedEmails = new HashSet<>();
        for (User user : input) {
            if (!usedUsers.contains(user)) {
                Queue<Email> queue = new LinkedList<>(user.getEmails());
                User component = new User(user.getName());
                usedUsers.add(user);
                while (!queue.isEmpty()) {
                    Email currentEmail = queue.poll();
                    if (!usedEmails.contains(currentEmail)) {
                        component.addEmail(currentEmail);
                        usedEmails.add(currentEmail);
                        for (User u : currentEmail.getUsers()) {
                            if (!usedUsers.contains(u)) {
                                usedUsers.add(u);
                                for (Email e : u.getEmails()) {
                                    if (!usedEmails.contains(e)) {
                                        queue.add(e);
                                    }
                                }
                            }
                        }
                    }
                }
                components.add(component);
            }
        }
        return components;
    }


}
