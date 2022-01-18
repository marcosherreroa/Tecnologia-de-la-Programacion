package simulator.model;

import java.util.List;

public interface SimulatorObserver {
	public void onRegister(List<Body> bodies, double currentTime, double deltaTime,String gLawsDesc);
	public void onReset(List<Body> bodies, double currentTime, double deltaTime,String gLawsDesc);
	public void onBodyAdded(List<Body> bodies, Body b);
	public void onAdvance(List<Body> bodies, double currentTime);
	public void onDeltaTimeChanged(double deltaTime);
	public void onGravityLawChanged(String gLawsDesc);
}
