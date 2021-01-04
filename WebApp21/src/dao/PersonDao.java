package dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import models.Person;

@Component
public class PersonDao {
	private static int PEOPLECOUNT;
	private List<Person> people;
	
	{
		people = new ArrayList<>();
		
		people.add(new Person(++PEOPLECOUNT, "Ivan"));
		people.add(new Person(++PEOPLECOUNT, "Anna"));
		people.add(new Person(++PEOPLECOUNT, "John"));
		people.add(new Person(++PEOPLECOUNT, "Veronika"));
		people.add(new Person(++PEOPLECOUNT, "Ilya"));
	}
	
	public List<Person> index(){
		return this.people;
	}
	
	public Person show(int id) {
		int newid=0;
		boolean checkid=false;
		for (int i =0; i<people.size(); i++) {
			if (people.get(i).getId() == id) {
				newid=i;
				checkid=true;
			}
		}
		if(checkid==false) {
			return new Person(id, "There is no person with such id");
		}
		return people.get(newid);
	}

	public void save(Person person) {
		// TODO Auto-generated method stub
		person.setId(++PEOPLECOUNT);
		people.add(person);
		
	}

	public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
    }
	
	 public void delete(int id) {
	        people.removeIf(p -> p.getId() == id);
	    }

}
