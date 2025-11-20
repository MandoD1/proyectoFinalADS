package services;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProfesorCatedraRepository;

@Service
public class ProfesorCatedraService extends ProfesorService<ProfesorCatedra> {

    @Autowired
    public ProfesorCatedraService(ProfesorCatedraRepository profesorCatedraRepository) {
        super(profesorCatedraRepository);
    }

    @Override
    protected void applyUpdates(ProfesorCatedra original, ProfesorCatedra updated) {
        original.setNombre(updated.getNombre());
        original.setEmail(updated.getEmail());
        original.setDepartamento(updated.getDepartamento());
        original.setPago(updated.getPago());
        original.setCodigo(updated.getCodigo());
        original.setClases(updated.getClases());
        original.setEmpresa(updated.getEmpresa());
        original.setCategoria(updated.getCategoria());
    }

    public double calcularPago(int horasDictadas, int minHoras, int maxHoras, ProfesorCatedra profesor) {

        boolean esCatedra = true;
        char categoria = profesor.getCategoria();

        if (horasDictadas < minHoras) {
            throw new IllegalArgumentException("El profesor no cumple el mínimo de horas permitido.");
        }

        if (esCatedra && horasDictadas > 19) {
            throw new IllegalArgumentException("Un profesor de cátedra no puede superar 19 horas.");
        }

        int tarifa;
        switch (Character.toUpperCase(categoria)) {
            case 'A' -> tarifa = 15000;
            case 'B' -> tarifa = 18000;
            case 'C' -> tarifa = 21000;
            case 'D' -> tarifa = 25000;
            case 'E' -> tarifa = 30000;
            default -> throw new IllegalArgumentException("Categoría no valida: " + categoria);
        }

        return ((long) horasDictadas * tarifa);
    }


}
