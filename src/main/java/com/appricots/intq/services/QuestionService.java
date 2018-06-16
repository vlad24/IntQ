package com.appricots.intq.services;

import com.appricots.intq.dao.impl.CategoryDAO;
import com.appricots.intq.dao.impl.DifficultyDAO;
import com.appricots.intq.dao.impl.LangDAO;
import com.appricots.intq.dao.impl.QuestionDAO;
import com.appricots.intq.model.Difficulty;
import com.appricots.intq.model.Lang;
import com.appricots.intq.model.Question;
import com.appricots.intq.wrappers.AliasedId;
import com.appricots.intq.wrappers.QuestionStatus;
import com.appricots.intq.wrappers.reqobjects.QuestionSelector;
import com.appricots.intq.wrappers.reqobjects.QuestionSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

	@Autowired
	private QuestionDAO qusetionDao;

	@Autowired
	private CategoryDAO categoryDao;

	@Autowired
	private LangDAO langDAO;

	@Autowired
	private DifficultyDAO difDAO;
	
	public QuestionService(QuestionDAO qDao, CategoryDAO categoryDao, LangDAO langDAO, DifficultyDAO difDAO) {
		super();
		this.qusetionDao = qDao;
		this.categoryDao = categoryDao;
		this.langDAO = langDAO;
		this.difDAO = difDAO;
	}

	@Transactional
	public Long tryAddSuggested(QuestionSuggestion suggestion) {
		Question question = new Question();
		Difficulty difficulty = difDAO.get(suggestion.getDifficulty());
		Lang language = langDAO.get(suggestion.getLanguage());
		if (language == null){
			throw new IllegalArgumentException("Illegal language provided " + suggestion.getLanguage());
		}
		question.setQuestion(suggestion.getQuestion());
		question.setAnswer(suggestion.getAnswer());
		question.setCategories(suggestion.getCategories().stream().map(c -> categoryDao.get(c)).collect(Collectors.toSet()));
		question.setDifficulty(difficulty);
		question.setLang(language);
		question.setAttachment(suggestion.getAttachment());
		question.setStatus(QuestionStatus.NEW);
		return qusetionDao.create(question);
	}

	@Transactional
	public Long addNew(Question q) {
		return qusetionDao.create(q);

	}

	@Transactional
	public Integer rate(long id, int delta) {
		Question question = qusetionDao.get(id);
		if (delta < 0){
			question.setMinusAmount(question.getMinusAmount() + 1);
		} else{
			question.setPlusAmount(question.getPlusAmount() + 1);
		}
		qusetionDao.update(question);
		return question.calculateRating();
	}

	@Transactional
	public Question getNext(QuestionSelector selector) {
		return qusetionDao.getNextAccepted(selector);
	}
	
	public List<AliasedId<Long>> getNew() {
		return qusetionDao.getAllNew().stream()
				.map(q -> new AliasedId<>(q.getId(), q.getQuestion()))
				.collect(Collectors.toList());
	}



}
