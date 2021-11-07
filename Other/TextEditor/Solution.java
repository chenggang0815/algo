package Other.TextEditor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
class Solution {
    static class TextEditor {
        class State {
            int cursor;
            int selectedLength;
            String content;

            public State(int cursor, int selectedLength, String content) {
                this.cursor = cursor;
                this.selectedLength = selectedLength;
                this.content = content;
            }
        }

        private TextEditorManager textEditorManager;
        // Stores the content of the text editor
        private StringBuilder content;

        // Stores the current position of the cursor
        private int cursor;
        private int selectedLength;

        private Stack<State> previousStateStack;
        private Stack<State> undoStack;

        public TextEditor(TextEditorManager textEditorManager) {
            // Empty content when the editor is opened.
            this.content = new StringBuilder();
            this.cursor = 0;
            this.selectedLength = 0;

            previousStateStack = new Stack<>();
            undoStack = new Stack<>();

            this.textEditorManager = textEditorManager;
        }

        public String append(String input) {
            saveCurrentState();
            clearUndoStack();
            int start = cursor - selectedLength;
//            System.out.print(start);
//            System.out.println(":");
            System.out.print(cursor);
//            System.out.print("=======");
            content.replace(start, cursor, input);

            cursor = start + input.length();
            selectedLength = 0;

            return content.toString();
        }

        public String delete() {
            saveCurrentState();
            clearUndoStack();

            int start = cursor - selectedLength;
            if (start == cursor) {
                content.deleteCharAt(start);
            } else {
                content.delete(start, cursor);
            }

            cursor = start;
            selectedLength = 0;

            return content.toString();
        }

        public void move(int position) {
            // Single cursor move clears the selected length.
            selectedLength = 0;

            if (position <= 0) {
                cursor = 0;
                return;
            }
           // System.out.println(content.length());
            if (cursor >= content.length()) {
                cursor = content.length();
                return;
            }
            System.out.println(position);
            cursor = position;
            System.out.println(cursor);
        }

        public String select(int selectPointer1, int selectPointer2) {
            int lowerBound = Math.min(selectPointer1, selectPointer2);
            int upperBound = Math.max(selectPointer1, selectPointer2);

            lowerBound = Math.max(0, lowerBound);
            upperBound = Math.min(content.length(), upperBound);

            // Set cursor to the upper bound.
            cursor = upperBound;
            selectedLength = upperBound - lowerBound;

            return content.substring(lowerBound, upperBound);
        }

        public String copy() {
            textEditorManager.sharedClipBoard = content.substring(cursor - selectedLength, cursor);

            return textEditorManager.sharedClipBoard;
        }

        public String paste() {
            return append(textEditorManager.sharedClipBoard);
        }

        public String undo() {
            // Push the current state to the undo stack.
            State currentState = new State(cursor, selectedLength, content.toString());
            undoStack.push(currentState);

            if (!previousStateStack.isEmpty()) {
                State previousState = previousStateStack.pop();
                content = new StringBuilder(previousState.content);
                cursor = previousState.cursor;
                selectedLength = previousState.selectedLength;
            } else {
                // Set to initial state.
                content = new StringBuilder();
                cursor = 0;
                selectedLength = 0;

            }

            return content.toString();
        }

        public String redo() {
            if (undoStack.isEmpty()) {
                throw new RuntimeException("Cannot Redo!");
            }

            // Push current state to previous state
            previousStateStack.push(new State(cursor, selectedLength, content.toString()));

            State beforeUndo = undoStack.pop();
            content = new StringBuilder(beforeUndo.content);
            cursor = beforeUndo.cursor;
            selectedLength = beforeUndo.selectedLength;

            return content.toString();
        }

        public void close() {
            selectedLength = 0;
            cursor = content.length();
            previousStateStack.clear();
            undoStack.clear();
        }

        private void saveCurrentState() {
            previousStateStack.push(new State(cursor, selectedLength, content.toString()));
        }

        private void clearUndoStack() {
            undoStack.clear();
        }
    }

    static class TextEditorManager {

        public String sharedClipBoard;

        Map<String, TextEditor> textEditorMap;
        Stack<TextEditor> activeTextEditorStack;

        public TextEditorManager() {
            this.sharedClipBoard = "";
            this.textEditorMap = new HashMap<>();
            this.activeTextEditorStack = new Stack<>();
        }

        public void open(String name) {
            // Empty file name?
            TextEditor textEditor = textEditorMap.getOrDefault(name, new TextEditor(this));
            textEditorMap.putIfAbsent(name, textEditor);
            activeTextEditorStack.push(textEditor);
        }

        public void close() {
            TextEditor textEditor = activeTextEditorStack.pop();
            textEditor.close();
        }

        public TextEditor getCurrentActiveTextEditor() {
            return activeTextEditorStack.peek();
        }

    }


    public static void main(String[] args) {
        TextEditorManager textEditorManager = new TextEditorManager();
        TextEditor text = new TextEditor(textEditorManager);

        //String[][] inputs = new String[][]{{"APPEND", "Hello! world!"}, {"MOVE", "5"}, {"DELETE", ""}, {"APPEND", ","}};
        String[][] inputs = new String[][]{{"APPEND","Never give up"},
                {"MOVE","12"},{"APPEND","!"},
                {"MOVE","11"},{"APPEND","!"},
                {"MOVE","10"},
        {"APPEND","!"},
                {"MOVE","09"},
        {"APPEND","!"},
                {"MOVE","08"},
        {"APPEND","!"},
                {"MOVE","07"},
        {"APPEND","!"},
                {"MOVE","06"},
        {"APPEND","!"} ,
                {"MOVE","05"},
        {"APPEND","!"},
                {"MOVE","04"},
        {"APPEND","!"},
                {"MOVE","03"},
        {"APPEND","!"},
                {"MOVE","02"},
        {"APPEND","!"},
                {"MOVE","01"},
        {"APPEND","!"}
        };

        for (String[] input : inputs){
            switch (input[0]){
                case "APPEND":
                    text.append(input[1]);
                    System.out.println(text.content);
                    break;
                case "MOVE":
                    System.out.println(Integer.parseInt(input[1]));
                    text.move(Integer.parseInt(input[1]));
                    System.out.println(text.content);
                    break;
                case "DELETE":
                    text.delete();
                    System.out.println(text.content);
                    break;
                case "SELECT":
                    String[] c = input[1].split(",");
                    text.select(Integer.parseInt(c[0]), Integer.parseInt(c[1]));
                    System.out.println(text.content);
                    break;
                case "COPY":
                    text.copy();
                    break;
                case "PASTE":
                    text.paste();
                    System.out.println(text.content);
                    break;
                case "UNDO":
                    text.undo();
                    System.out.println(text.content);
                    break;
                case "REDO":
                    text.redo();
                    System.out.println(text.content);
                    break;
            }
        }


    }
}
