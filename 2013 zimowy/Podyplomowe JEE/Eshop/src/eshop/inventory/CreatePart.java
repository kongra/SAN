package eshop.inventory;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inventory/CreatePart")
public class CreatePart extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @EJB
  private Inventory inventory;

  public CreatePart() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // String code = "C0805 0u1";
    //
    // Map<Prop, PropValue> propValues = new HashMap<>();
    // propValues.put(inventory.prop("capacity"), inventory.propValue("0.1uF"));
    // propValues.put(inventory.prop("Voltage Rating"),
    // inventory.propValue("10V"));
    // PartType type = inventory.partType("C0805", propValues);

    Map<Prop, PropValue> jogoProps = new HashMap<>();
    jogoProps.put(inventory.prop("zawartość tłuszczu"),
      inventory.propValue("3%"));
    InventoryPart jogo = inventory.inventoryPart("Jogurt Zott", jogoProps, null);

    Map<Prop, PropValue> butterProps = new HashMap<>();
    butterProps.put(inventory.prop("zawartość tłuszczu"),
      inventory.propValue("85%"));
    InventoryPart łaciate = inventory.inventoryPart("Masło Łaciate", butterProps, null);

    InventoryPart promocja =
        inventory.inventoryPart("Jogurt + Masełko", null,
          new HashSet<>(Arrays.asList(jogo, łaciate)));

  }

}
