package ed.san.inventory.equipment;

import ed.san.inventory.employee.Employee;

public interface EquipmentFacade {

  void bind(EquipmentWithSerialNumber equipment, Employee employee);

}
