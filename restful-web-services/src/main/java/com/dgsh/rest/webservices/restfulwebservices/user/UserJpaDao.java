package com.dgsh.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserJpaDao {
	//JPA/Hibernate to store into database
	
	public static List<User> list1 = new ArrayList<User>();
	private static int userCount=0;
	static {
		list1.add(new User(++userCount,"Durgesh",LocalDate.now().minusYears(23)));
		list1.add(new User(++userCount,"Neeraj",LocalDate.now().minusYears(24)));
		list1.add(new User(++userCount,"Prince",LocalDate.now().minusYears(26)));
	}
	
	public List<User> findAll(){
		return list1;
	}
	
	public User createUser(User user) {
		user.setId(++userCount);
		list1.add(user);
		System.out.println(user);
		return user;
	}
	
	public User findOne(int id) {
		//stream and lambda
		 return list1.stream().filter(e->e.getId().equals(id)).findFirst().orElse(null);
		
		//old way
		//User user1  = null;
		//for(User user:list1) {
		//	if(user.getId()==id) {
		//		user1 =user;
		//	}
		//}
		//return user1;
	}

	
	public void deleteOne(int id) {
		//stream and lambda
		Predicate<? super User> predicate = e->e.getId().equals(id);
		list1.stream().filter(predicate).findFirst().orElse(null);
		list1.removeIf(predicate);
	}

	
}
