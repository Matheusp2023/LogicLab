package org.ufal.logiclabfilesupport.file;
/** Localizable strings for {@link org.ufal.logiclabfilesupport.file}. */
class Bundle {
    /**
     * @return <i>Source</i>
     * @see LogicDiagramDataObject
     */
    static String LBL_LogicDiagram_EDITOR() {
        return org.openide.util.NbBundle.getMessage(Bundle.class, "LBL_LogicDiagram_EDITOR");
    }
    /**
     * @return <i>Files of LogicDiagram</i>
     * @see LogicDiagramDataObject
     */
    static String LBL_LogicDiagram_LOADER() {
        return org.openide.util.NbBundle.getMessage(Bundle.class, "LBL_LogicDiagram_LOADER");
    }
    /**
     * @return <i>Visual</i>
     * @see LogicDiagramVisualElement
     */
    static String LBL_LogicDiagram_VISUAL() {
        return org.openide.util.NbBundle.getMessage(Bundle.class, "LBL_LogicDiagram_VISUAL");
    }
    private Bundle() {}
}
