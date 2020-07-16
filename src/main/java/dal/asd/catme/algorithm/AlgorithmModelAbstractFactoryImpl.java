package dal.asd.catme.algorithm;


public class AlgorithmModelAbstractFactoryImpl implements IAlgorithmModelAbstractFactory{
    private static IAlgorithmModelAbstractFactory modelAbstractFactory = null;

    public static IAlgorithmModelAbstractFactory instance()
    {
        if(modelAbstractFactory==null)
        {
            modelAbstractFactory = new AlgorithmModelAbstractFactoryImpl();
        }
        return modelAbstractFactory;
    }
   
	@Override
	public IAlgorithmParameters makeAlgorithmParameters() {
		return new AlgorithmParameters();
	}
	@Override
	public IQuestion makeQuestion() {
		return new Question();
	}

}
