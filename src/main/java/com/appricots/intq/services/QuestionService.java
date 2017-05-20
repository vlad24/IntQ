package com.appricots.intq.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appricots.intq.dao.impl.CategoryDAO;
import com.appricots.intq.dao.impl.DifficultyDAO;
import com.appricots.intq.dao.impl.LangDAO;
import com.appricots.intq.dao.impl.QuestionDAO;
import com.appricots.intq.model.Category;
import com.appricots.intq.model.Difficulty;
import com.appricots.intq.model.Lang;
import com.appricots.intq.model.Question;
import com.appricots.intq.wrappers.DBAccessReport;
import com.appricots.intq.wrappers.QuestionSelector;
import com.appricots.intq.wrappers.QuestionStatus;
import com.appricots.intq.wrappers.QuestionSuggestion;

@Service
public class QuestionService {

	@Autowired
	QuestionDAO qDao;
	
	@Autowired
	CategoryDAO categoryDao;

	@Autowired
	private LangDAO langDAO;

	@Autowired
	private DifficultyDAO difDAO;
	
	
	public QuestionService(QuestionDAO dao) {
		qDao = dao;
	}
	


	@Transactional
	public DBAccessReport tryAddSuggested(QuestionSuggestion suggestion) {
		DBAccessReport report = new DBAccessReport();
		try{
			Question q = new Question();
			Difficulty difficulty = difDAO.findByAlias(suggestion.getDifficulty());
			Lang language = langDAO.findByAlias(suggestion.getLanguage());
			if (language == null){
				throw new IllegalArgumentException("Illegal language provided " + suggestion.getLanguage());
			}
			Set<Category> categories = new HashSet<Category>();
			for (String alias: suggestion.getCategories()){
				Category category = categoryDao.findByAlias(alias);
				if (category == null){
					throw new IllegalArgumentException("Illegal category provided: " + alias);
				}else{
					categories.add(category);
				}
			}
			q.setQuestion(suggestion.getQuestion());
			q.setAnswer(suggestion.getAnswer());
			q.setCategories(categories);
			q.setDifficulty(difficulty);
			q.setLang(language);
			q.setAttachment(suggestion.getAttachment());
		}catch(IllegalArgumentException e){
			report.setErrorMessage("Inconsistent request data:" + e.getMessage());
		}catch(Exception err){
			report.setErrorMessage("Error occured:" + err.getMessage());
		}
		return null;
	}
	
	@Transactional
	public void addNew(Question q) {
		qDao.create(q);
		
	}

	@Transactional
	public double rate(long id, String delta) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Transactional
	public Question getNext(QuestionSelector selector) {
		return qDao.getNextAccepted(selector);
	}


	@Transactional
	public void debugInit() {
		Category   pythonCategory  = new Category("Python");
		Category   testingCategory = new Category("Testing");
		Category   javaCategory    = new Category("Java");
		Difficulty easy            = new Difficulty("Easy");
		Difficulty medium          = new Difficulty("Medium");
		Difficulty hard            = new Difficulty("Hard");
		Lang       english         = new Lang("English");
		Lang       russian         = new Lang("Russian");
		
		Set<Category> categoriesOfQ1 = new HashSet<Category>(Arrays.asList(pythonCategory));
		Set<Category> categoriesOfQ2 = new HashSet<Category>(Arrays.asList(testingCategory));
		Set<Category> categoriesOfQ3 = new HashSet<Category>(Arrays.asList(testingCategory, pythonCategory));
		Set<Category> categoriesOfQ4 = new HashSet<Category>(Arrays.asList(javaCategory));
		Set<Category> categoriesOfQ5 = new HashSet<Category>(Arrays.asList(javaCategory));
		
		Question q1 = new Question(
				"Python and multi-threading. Is it a good idea? List some ways to get some Python code to run in a parallel way.",
				
				"Python doesn't allow multi-threading in the truest sense of the word. It has a multi-threading package but if you want to multi-thread to speed your code up, then it's usually not a good idea to use it. "
				+ "Python has a construct called the Global Interpreter Lock (GIL). The GIL makes sure that only one of your 'threads' "
				+ "can execute at any one time. A thread acquires the GIL, does a little work, then passes the GIL onto the next thread. This happens very quickly so to the human eye it may "
				+ "seem like your threads are executing in parallel, but they are really just taking turns using the same CPU core. All this GIL passing adds overhead to execution. "
				+ "This means that if you want to make your code run faster then using the threading package often isn't a good idea.",
				
				english,
				medium,
				categoriesOfQ1,
				null, 
				QuestionStatus.ACCEPTED
		);
		Question q2 = new Question(
				"What is Black Box Testing?",
				
				"Testing based on an analysis of the specification of a piece of software without reference to its internal workings. "
				 + "The goal is to test how well the component conforms to the published requirements for the component.",				
				english,
				easy,
				categoriesOfQ2,
				null,
				QuestionStatus.ACCEPTED
		);
		Question q3 = new Question(
				"What is unittest in Python?",
				"A unit testing framework in Python is known as unittest.  It supports sharing of setups, automation testing, shutdown code for tests, aggregation of tests into collections etc.",
				english,
				easy,
				categoriesOfQ3,
				null,
				QuestionStatus.ACCEPTED
		);
		Question q4 = new Question(
				"What is Read-Write Lock? Does ConcurrentHashMap in Java Uses The ReadWrite Lock?",
				
				"ReadWrite Lock is an implementation of lock stripping technique, where two separate locks are used for read and write operation. Since read operation doesn't modify the state of the object, it's safe to allow multiple thread access "
				+ "to a shared object for reading without locking, and by splitting one lock into read and write lock, you can easily do that." 
				+"Java provides an implementation of read-write lock in the form of ReentrantReadWritLock class in the java.util.concurrent.lock package. This is worth looking before you decide to write your own read-write locking implementation. "
				,
				english,
				hard,
				categoriesOfQ4,
				null,
				QuestionStatus.ACCEPTED
		);
		Question q5 = new Question(
				"Какова цель BiConsumer <T, U> функциональный интерфейс?",
				
				"Она представляет собой операцию, которая принимает два входных аргумента и не возвращает никакого результата.",
				russian,
				easy,
				categoriesOfQ5,
				null,
				QuestionStatus.ACCEPTED
		);
		pythonCategory.setQuestions( new HashSet<Question>(Arrays.asList(q1, q3)));
		testingCategory.setQuestions(new HashSet<Question>(Arrays.asList(q2, q3)));
		javaCategory.setQuestions(   new HashSet<Question>(Arrays.asList(q4, q5)));
		categoryDao.create(javaCategory);
		categoryDao.create(pythonCategory);
		categoryDao.create(testingCategory);
		difDAO.create(easy);
		difDAO.create(medium);
		difDAO.create(hard);
		langDAO.create(russian);
		langDAO.create(english);
		System.out.println("------ adding q1");
		addNew(q1);
		System.out.println("------ adding q2");
		addNew(q2);
		System.out.println("------ adding q3");
		addNew(q3);
		System.out.println("------ adding q4");
		addNew(q4);
		System.out.println("------ adding q5");
		addNew(q5);
		System.out.println("------ added all");
	}


}
