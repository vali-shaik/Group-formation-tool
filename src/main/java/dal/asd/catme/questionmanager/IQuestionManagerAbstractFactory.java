package dal.asd.catme.questionmanager;

public interface IQuestionManagerAbstractFactory
{
    IQuestionDao getQuestionDao();
    IQuestionManagerService getQuestionManagerService();
    IListQuestionsService getListQuestionsService();
}
