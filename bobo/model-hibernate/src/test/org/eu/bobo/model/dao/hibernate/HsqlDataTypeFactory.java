/*                                                   *\
 * Copyright © 2004 Centre Informatique Multimédia   *
 *                                                   *
 * $Id: HsqlDataTypeFactory.java,v 1.1 2005/01/13 13:38:06 romale Exp $
\*                                                   */


package org.eu.bobo.model.dao.hibernate;
import java.sql.Types;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/01/13 13:38:06 $
 */
public class HsqlDataTypeFactory extends DefaultDataTypeFactory {
    //~ Méthodes ---------------------------------------------------------------

    public DataType createDataType(int jdbcType, String name)
      throws DataTypeException {
        if (Types.BOOLEAN == jdbcType) {
            return DataType.INTEGER;
        }

        return super.createDataType(jdbcType, name);
    }
}
