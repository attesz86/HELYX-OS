/*******************************************************************************
 *  |       o                                                                   |
 *  |    o     o       | HELYX-OS: The Open Source GUI for OpenFOAM             |
 *  |   o   O   o      | Copyright (C) 2012-2016 ENGYS                          |
 *  |    o     o       | http://www.engys.com                                   |
 *  |       o          |                                                        |
 *  |---------------------------------------------------------------------------|
 *  |   License                                                                 |
 *  |   This file is part of HELYX-OS.                                          |
 *  |                                                                           |
 *  |   HELYX-OS is free software; you can redistribute it and/or modify it     |
 *  |   under the terms of the GNU General Public License as published by the   |
 *  |   Free Software Foundation; either version 2 of the License, or (at your  |
 *  |   option) any later version.                                              |
 *  |                                                                           |
 *  |   HELYX-OS is distributed in the hope that it will be useful, but WITHOUT |
 *  |   ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or   |
 *  |   FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License   |
 *  |   for more details.                                                       |
 *  |                                                                           |
 *  |   You should have received a copy of the GNU General Public License       |
 *  |   along with HELYX-OS; if not, write to the Free Software Foundation,     |
 *  |   Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA            |
 *******************************************************************************/
package eu.engys.gui.solver;

import eu.engys.core.controller.Command;
import eu.engys.core.controller.Controller;
import eu.engys.core.project.state.ServerState;
import eu.engys.gui.solver.postprocessing.ServerListener;

public class ReloadOnFinish implements ServerListener {

    private Controller controller;

    public ReloadOnFinish(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void serverChanged(ServerState serverState) {
        if (serverState.getCommand().equals(Command.RUN_CASE) || serverState.getCommand().equals(Command.RUN_ALL)) {
            if (serverState.getSolverState().isFinished() || serverState.getSolverState().isError()) {
                if (controller.getListener() != null) {
                    controller.getListener().afterRunCase();
                }
            }
        }
    }
}
