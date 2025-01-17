package youngmin.util;

import youngmin.calc.YoungminCalc;

public class PerfectCalc extends YoungminCalc {
	
	@Override
	public int addition(int... x)
	{
		int result = super.addition(x);	
		System.out.println("더한 결과 : " + result);
		
		return result;
	}
	
	public int multiplication(int x, int y)
	{
		return x * y;
	}
	
	public float Division(int x, int y)
	{
		float result = 0.f;
		
		try
		{
			result = x / y;
		}
		catch (ArithmeticException e)
		{
			System.out.println("0으로는 나눌 수 없음.");
			result = 1.f;
		}
		
		return result;
	}
}
