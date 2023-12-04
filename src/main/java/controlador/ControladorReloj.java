package controlador;

import modelo.Alerta;

import java.util.Calendar;

public class ControladorReloj extends Thread {

    private String horaMostrada;


    private OnUpdateListener onUpdateListener;


    public interface OnUpdateListener {
        void onUpdate(String horaMostrada);
    }

    public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
        this.onUpdateListener = onUpdateListener;
    }

    public void run(){
        while (true) {


            try {
                Calendar c = Calendar.getInstance();
                int hora = c.get(Calendar.HOUR);
                int minutos = c.get(Calendar.MINUTE);
                int segundos = c.get(Calendar.SECOND);

                String tiempoHora = (hora + ":" + minutos + ":" + segundos);
                horaMostrada = tiempoHora;


                if (onUpdateListener != null) {
                    onUpdateListener.onUpdate(tiempoHora);
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Alerta alertaHora = new Alerta("Error", "Error con la hora");
                alertaHora.mostrarAlertaError();
            }
        }
    }

    public String getHoraMostrada() {
        return horaMostrada;
    }


}

