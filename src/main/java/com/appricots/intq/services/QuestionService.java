package com.appricots.intq.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
import com.appricots.intq.wrappers.AliasedId;
import com.appricots.intq.wrappers.QuestionSelector;
import com.appricots.intq.wrappers.QuestionStatus;
import com.appricots.intq.wrappers.QuestionSuggestion;

@Service
public class QuestionService {

	@Autowired
	private QuestionDAO qDao;

	@Autowired
	private CategoryDAO categoryDao;

	@Autowired
	private LangDAO langDAO;

	@Autowired
	private DifficultyDAO difDAO;
	
	Long cachedIdOfAny = null;

	public QuestionService(QuestionDAO qDao, CategoryDAO categoryDao,
			LangDAO langDAO, DifficultyDAO difDAO) {
		super();
		this.qDao = qDao;
		this.categoryDao = categoryDao;
		this.langDAO = langDAO;
		this.difDAO = difDAO;
	}

	@Transactional
	public Long tryAddSuggested(QuestionSuggestion suggestion) {
		System.out.println(suggestion);
		Question q = new Question();
		Difficulty difficulty = difDAO.get(suggestion.getDifficulty());
		Lang language = langDAO.get(suggestion.getLanguage());
		if (language == null){
			throw new IllegalArgumentException("Illegal language provided " + suggestion.getLanguage());
		}
		Set<Category> cats = new HashSet<Category>();
		for (Long cid : suggestion.getCategories()){
			cats.add(categoryDao.get(cid));
		}
		q.setQuestion(suggestion.getQuestion());
		q.setAnswer(suggestion.getAnswer());
		q.setCategories(cats);
		q.setDifficulty(difficulty);
		q.setLang(language);
		q.setAttachment(suggestion.getAttachment());
		q.setStatus(QuestionStatus.NEW);
		return qDao.create(q);
	}

	@Transactional
	public Long addNew(Question q) {
		return qDao.create(q);

	}

	@Transactional
	public Integer rate(long id, int delta) {
		Question q = qDao.get(id);
		if (delta < 0){
			q.setMinusAmount(q.getMinusAmount() + 1);
		}else{
			q.setPlusAmount(q.getPlusAmount() + 1);
		}
		qDao.update(q);
		return Integer.valueOf(q.calculateRating());
	}

	@Transactional
	public Question getNext(QuestionSelector selector) {
		return qDao.getNextAccepted(selector);
	}
	
	public List<AliasedId<Long>> getNew() {
		List<Question> qs =  qDao.getNew();
		List<AliasedId<Long>> aids = new ArrayList<AliasedId<Long>>(qs.size());
		for (Question q: qs){
			aids.add(new AliasedId<Long>(q.getId(), q.getQuestion()));
		}
		return aids;
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
		Set<Category> categoriesOfQ6 = new HashSet<Category>(Arrays.asList(javaCategory));

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
						medium,
						categoriesOfQ4,
						null,
						QuestionStatus.ACCEPTED
				);
		Question q5 = new Question(
				"Какова цель BiConsumer <T, U> функциональный интерфейс?",

				"Она представляет собой операцию, которая принимает два входных аргумента и не возвращает никакого результата.",
				russian,
				medium,
				categoriesOfQ5,
				null,
				QuestionStatus.ACCEPTED
				);
		Question q6 = new Question(
				"Who invented Java?",
				"James Gosling!",
				english,
				easy,
				categoriesOfQ6,
				null,
				QuestionStatus.ACCEPTED
				);
		Question q7 = new Question(
				"Are you cool?",
				"Oh, yeah!",
				english,
				easy,
				categoriesOfQ4,
				null,
				QuestionStatus.NEW
				);
		categoryDao.create(javaCategory);
		categoryDao.create(pythonCategory);
		categoryDao.create(testingCategory);
		difDAO.create(easy);
		difDAO.create(medium);
		difDAO.create(hard);
		langDAO.create(english);
		langDAO.create(russian);
		addNew(q1);
		addNew(q2);
		addNew(q3);
		addNew(q4);
		addNew(q5);
		addNew(q6);
		addNew(q7);
	}



}
