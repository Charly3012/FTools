package controlador;

import modelo.Alerta;

import java.util.Calendar;

public class ControladorReloj extends Thread {

    private String horaMostrada;
    private String fechaMostrada;



    private OnUpdateListener onUpdateListener;


    public interface OnUpdateListener {
        void onUpdate(String horaMostrada);

        void onUpdate(String horaMostrada, String fechaMostrada);
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
                int dia = c.get(Calendar.DATE);
                int mes = c.get(Calendar.MONTH);
                int anno = c.get(Calendar.YEAR);

                fechaMostrada = dia + "/"+ mes + "/" + anno;

                String tiempoHora = (hora + ":" + minutos + ":" + segundos);
                horaMostrada = tiempoHora;


                if (onUpdateListener != null) {
                    onUpdateListener.onUpdate(tiempoHora);
                    onUpdateListener.onUpdate(fechaMostrada);
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

    public String getFechaMostrada(){
        return fechaMostrada;
    }


}

