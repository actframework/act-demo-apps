package demo.event;

public class InstantWelcomeEventProcessor extends WelcomeEventProcessor  {
    @Override
    public void on(WelcomeEvent event) {
        logger.info("[Instant]%s, welcome!", event.source().who());
    }
}
