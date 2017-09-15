package padawans;

import java.util.Date;
import java.util.*;

/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * 
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
public class StudentGroup implements StudentArrayOperation {

	private Student[] students;
	private LinkedList<Student> list;
	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
		list = new LinkedList<Student>();
	}

	@Override
	public Student[] getStudents() {
		return this.students;
	}

	@Override
	public void setStudents(Student[] students) {
		// Add your implementation here
		for(Student st : this.students){
			if(st == null){
				throw new IllegalArgumentException();
			}else{
				list.addLast(st);
			}
		}
		this.students = list.toArray(new Student[list.size()]);
	}

	@Override
	public Student getStudent(int index) {
		if (index < 0 || index >= this.students.length)
			throw new IllegalArgumentException();
		else
			return this.students[index];
		
	}

	@Override
	public void setStudent(Student student, int index) {
		if (index < 0 || index >= this.students.length || student == null)
			throw new IllegalArgumentException();
		
		list.remove(index);
		list.add(index,student);
		this.students = list.toArray(new Student[list.size()]);
	}

	@Override
	public void addFirst(Student student) {
		if (student == null)
			throw new IllegalArgumentException();
		list.addFirst(student);
		this.students = list.toArray(new Student[list.size()]);
	}

	@Override
	public void addLast(Student student) {
		if (student == null)
			throw new IllegalArgumentException();
		list.addLast(student);
		this.students = list.toArray(new Student[list.size()]);
	}

	@Override
	public void add(Student student, int index) {
		if (index < 0 || index >= this.students.length || student == null)
			throw new IllegalArgumentException();
		
		list.add(index,student);
		this.students = list.toArray(new Student[list.size()]);
	}

	@Override
	public void remove(int index) {
		if (index < 0 || index >= this.students.length )
			throw new IllegalArgumentException();
		
		list.remove(index);
		this.students = list.toArray(new Student[list.size()]);
		
	}

	@Override
	public void remove(Student student) {
		if (student == null )
			throw new IllegalArgumentException();
		
		if(list.indexOf(student) == -1)
			throw new IllegalArgumentException("Student not exist");
		else
			list.remove(student);
		this.students = list.toArray(new Student[list.size()]);
	}

	@Override
	public void removeFromIndex(int index) {
		if (index < 0 || index >= this.students.length)
			throw new IllegalArgumentException();
		for(int i = this.students.length - index ; i>1; i--)
			list.remove(index + 1);
		this.students = list.toArray(new Student[list.size()]);
	}

	@Override
	public void removeFromElement(Student student) {
		if (student == null)
			throw new IllegalArgumentException();
		int index = list.indexOf(student) ;
		for(int i = this.students.length - index ; i>1; i--)
			list.remove(index + 1);
		this.students = list.toArray(new Student[list.size()]);
	}

	@Override
	public void removeToIndex(int index) {
		if (index < 0 || index >= this.students.length)
			throw new IllegalArgumentException();
		for(int i = 0 ; i < index - 1 ; i++)
			list.remove(0);
		this.students = list.toArray(new Student[list.size()]);
	}

	@Override
	public void removeToElement(Student student) {
		if (student == null)
			throw new IllegalArgumentException();
		int index = list.indexOf(student);
		for(int i = 0 ; i < index - 1 ; i++)
			list.remove(0);
		this.students = list.toArray(new Student[list.size()]);
	}

	@Override
	public void bubbleSort() {
		int len = this.students.length - 1, t3;
		Student temp;
		for (int t1 = len; t1 >= 0; t1--) {
            for (int t2 = 0; t2 < len - 1; t2++) {
                t3 = t2 + 1;
                if (this.students[t2].getId() > this.students[t3].getId()) {
                    temp = this.students[t2];
                    this.students[t2] = this.students[t3];
                    this.students[t3] = temp;
                }
            }
        }
	}

	@Override
	public Student[] getByBirthDate(Date date) {
		ArrayList<Student> array = new ArrayList<Student>();
		if(date == null)
			throw new IllegalArgumentException();
		for(Student st : this.students){
			if(date.before(st.getBirthDate()) || date.equals(st.getBirthDate())) 
				array.add(st);
		}
		
		return array.toArray(new Student[array.size()]);
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		ArrayList<Student> array = new ArrayList<Student>();
		if(firstDate == null || lastDate == null)
			throw new IllegalArgumentException();
		for(Student st : this.students){
			if((lastDate.before(st.getBirthDate()) || lastDate.equals(st.getBirthDate())) && (firstDate.after(st.getBirthDate()) || firstDate.equals(st.getBirthDate()))) 
				array.add(st);
		}
		
		return array.toArray(new Student[array.size()]);
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		ArrayList<Student> array = new ArrayList<Student>();
		if(date == null)
			throw new IllegalArgumentException();
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); 
        for(Student st : this.students){
			if(date.before(st.getBirthDate()) || date.equals(st.getBirthDate()) || cal.getTime().before(st.getBirthDate()) || cal.getTime().equals(st.getBirthDate())) 
				array.add(st);
		}
		
		return array.toArray(new Student[array.size()]);
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		if(indexOfStudent == 0)
			throw new IllegalArgumentException();
		
		Date date = this.students[indexOfStudent].getBirthDate();
		return Calendar.getInstance().get(Calendar.YEAR) - date.getYear();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Student[] getStudentsByAge(int age) {
		ArrayList<Student> array = new ArrayList<Student>();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for(Student st : this.students){
			if(currentYear - st.getBirthDate().getYear() == age)
				array.add(st);
		}
		return array.toArray(new Student[array.size()]);
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		ArrayList<Student> array = new ArrayList<Student>();
		int avgMarks = 0;
		for(Student st : this.students){
			avgMarks += st.getAvgMark();
		}
		avgMarks /= this.students.length;
		for(Student st : this.students){
			if(st.getAvgMark() >= avgMarks)
				array.add(st);
		}
		
		return array.toArray(new Student[array.size()]);
	}

	@Override
	public Student getNextStudent(Student student) {
		if(student == null)
			throw new IllegalArgumentException();
		int index = this.list.indexOf(student);
		if(index == -1)
			return null;
		else if(index == this.students.length-1)
			return null;
		
		return this.list.get(index+1) ;
	}
}
