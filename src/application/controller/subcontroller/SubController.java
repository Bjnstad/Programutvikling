package application.controller.subcontroller;

import application.controller.Controller;

/**
 * Interface to sub controller that
 */
public interface SubController {
    void setSubController(Controller controller);

    void init();
}
