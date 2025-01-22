package rt.presenter;

import rt.model.Service;
import rt.view.View;

public class Presenter {

    private Service service;
    private View view;

    public Presenter(Service service, View view) {
        this.service = service;
        this.view = view;
    }
    //TODO Класс для обеспечения связи между моделью и отображением
}
