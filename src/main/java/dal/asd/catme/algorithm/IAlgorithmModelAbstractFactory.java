package dal.asd.catme.algorithm;

public interface IAlgorithmModelAbstractFactory {
	IAlgorithmParameters makeAlgorithmParameters();
	IQuestion makeQuestion();
	
}
