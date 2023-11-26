package edu.san.logic.inventory.equipment;

import edu.san.logic.inventory.employee.Employee;

public interface EquipmentFacade {

  void bind(EquipmentWithSerialNumber equipment, Employee employee);

}
